package com.rem.reactive_programming_playground.sec09;

import com.rem.reactive_programming_playground.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec05Merge {

    private static final Logger log = LoggerFactory.getLogger(Lec05Merge.class);

    public static void main(String[] args) {
        demo2();
        Util.sleepSeconds(3);
    }

    private static void demo1() {
       Flux.merge(producer1(), producer2(), producer3())
               .take(2)
               .subscribe(Util.subscriber());
    }

    private static void demo2() {
        producer1()
                .mergeWith(producer2())
                .mergeWith(producer3())
                .take(2)
                .subscribe(Util.subscriber());
    }

    private static Flux<Integer> producer1() {
        return Flux.just(1, 2, 3, 4, 5)
                .transform(Util.fluxLogger("producer 1"))
                .delayElements(Duration.ofMillis(10));
    }
    private static Flux<Integer> producer2() {
        return Flux.just(52, 53, 54)
                .transform(Util.fluxLogger("producer 2"))
                .delayElements(Duration.ofMillis(10));
    }

    private static Flux<Integer> producer3() {
        return Flux.just(12, 13, 14)
                .transform(Util.fluxLogger("producer 3"))
                .delayElements(Duration.ofMillis(10));
    }
}
