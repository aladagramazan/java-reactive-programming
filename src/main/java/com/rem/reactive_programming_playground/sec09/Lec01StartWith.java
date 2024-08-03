package com.rem.reactive_programming_playground.sec09;

import com.rem.reactive_programming_playground.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

public class Lec01StartWith {

    private static final Logger log = LoggerFactory.getLogger(Lec01StartWith.class);

    public static void main(String[] args) {
       demo4();
        Util.sleepSeconds(3);
    }

    private static void demo1() {
        producer1()
                .startWith(-1, 0)
                .subscribe(Util.subscriber());
    }

    private static void demo2() {
        producer1()
                .startWith(List.of(1, 2,3))
                .subscribe(Util.subscriber());
    }

    private static void demo3() {
        producer1()
                .startWith(producer2())
                .subscribe(Util.subscriber());
    }

    private static void demo4() {
        producer1()
                .startWith(producer2())
                .startWith(1000)
                .subscribe(Util.subscriber());
    }

    private static Flux<Integer> producer1() {
        return Flux.just(1, 2, 3, 4, 5)
                .doOnSubscribe(s -> log.info("subscribed to producer 1"))
                .delayElements(Duration.ofMillis(10));

    }

    private static Flux<Integer> producer2() {
        return Flux.just(52,53,54)
                .doOnSubscribe(s -> log.info("subscribed to producer 2"))
                .delayElements(Duration.ofMillis(10));
    }
}
