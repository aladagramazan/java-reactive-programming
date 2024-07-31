package com.rem.reactive_programming_playground.sec08;

import com.rem.reactive_programming_playground.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec03MultipleSubscribers {

    private static final Logger log = LoggerFactory.getLogger(Lec03MultipleSubscribers.class);

    public static void main(String[] args) {

        var publisher = Flux.generate(
                        () -> 1,
                        (state, sink) -> {
                            log.info("generating: {}", state);
                            sink.next(state);
                            return ++state;
                        }
                ).cast(Integer.class)
                .subscribeOn(Schedulers.parallel());  // generate on parallel thread

        publisher  // slow subscriber
                .limitRate(5) // we say to the producer to produce 5 elements at a time
                .publishOn(Schedulers.boundedElastic()) // consume on boundedElastic thread
                .map(Lec03MultipleSubscribers::timeConsumingProcess)
                .subscribe(Util.subscriber("sub1"));

        publisher  // fast subscriber
                .take(100)
                .publishOn(Schedulers.boundedElastic())
                .subscribe(Util.subscriber("sub2"));

        Util.sleepSeconds(60);

        // producer is faster than consumer
    }

    private static int timeConsumingProcess(int i) {
        Util.sleepSeconds(1);
        log.info("time consuming task: {}", i);
        return i;
    }
}
