package com.rem.reactive_programming_playground.sec09;

import com.rem.reactive_programming_playground.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec04ConcatWithError {

    private static final Logger log = LoggerFactory.getLogger(Lec04ConcatWithError.class);

    public static void main(String[] args) {
        demo2();
        Util.sleepSeconds(3);
    }

    private static void demo1() {
        producer1()
                .concatWith(producer3())
                .concatWith(producer2())
                .subscribe(Util.subscriber());
    }

    private static void demo2() {
       Flux.concatDelayError(producer1(), producer3(), producer2())
               .subscribe(Util.subscriber());
    }

    private static Flux<Integer> producer1() {
        return Flux.just(1, 2, 3, 4, 5)
                .doOnSubscribe(s -> log.info("subscribed to producer 1"))
                .delayElements(Duration.ofMillis(10));

    }

    private static Flux<Integer> producer2() {
        return Flux.just(52, 53, 54)
                .doOnSubscribe(s -> log.info("subscribed to producer 2"))
                .delayElements(Duration.ofMillis(10));
    }

    private static Flux<Integer> producer3() {
        return Flux.error(new IllegalArgumentException("oops"));
    }
}
