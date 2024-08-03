package com.rem.reactive_programming_playground.sec09;

import com.rem.reactive_programming_playground.common.Util;
import com.rem.reactive_programming_playground.sec09.helper.NameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec03ConcatWith {

    private static final Logger log = LoggerFactory.getLogger(Lec03ConcatWith.class);

    public static void main(String[] args) {
        demo3();
        Util.sleepSeconds(3);
    }

    private static void demo1() {
        producer1()
                .concatWithValues(-1, 0)  // producer1'daki item bitince -1 ve 0'ı ekler
                .subscribe(Util.subscriber());
    }

    private static void demo2() {
        producer1()
                .concatWith(producer2()) // producer1'daki item bitince producer2'yi ekler
                .take(2)
                .subscribe(Util.subscriber());
    }

    private static void demo3() {
        Flux.concat(producer1(), producer2())
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
}
