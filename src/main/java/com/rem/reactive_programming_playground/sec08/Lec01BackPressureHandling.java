package com.rem.reactive_programming_playground.sec08;

import com.rem.reactive_programming_playground.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import reactor.util.concurrent.Queues;

public class Lec01BackPressureHandling {

    //Queues

    //  System.setProperty("reactor.bufferSize.small", "256");

    private static final Logger log = LoggerFactory.getLogger(Lec01BackPressureHandling.class);

    public static void main(String[] args) {
        System.setProperty("reactor.bufferSize.small", "16");

        var publisher = Flux.generate(
                        () -> 1,
                        (state, sink) -> {
                            log.info("generating: {}", state);
                            sink.next(state);
                            return ++state;
                        }
                ).cast(Integer.class)
                .subscribeOn(Schedulers.parallel());  // generate on parallel thread

        // consumer kuyruktan elemanların % 75'ini alır ve işler
        // producer bakar kuyruğun yüzde 75'i boş olduğunda yeni eleman ekler, sonra durur.
        // consumer elemanların yüzde 75'ini okuduktan sonra producer tekrar eleman üretir.

        publisher
                .publishOn(Schedulers.boundedElastic()) // consume on boundedElastic thread
                .map(Lec01BackPressureHandling::timeConsumingProcess)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);

        // producer is faster than consumer
    }

    private static int timeConsumingProcess(int i) {
        Util.sleepSeconds(1);
        log.info("time consuming task: {}", i);
        return i;
    }
}
