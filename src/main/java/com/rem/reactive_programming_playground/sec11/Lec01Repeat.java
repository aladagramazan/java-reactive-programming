package com.rem.reactive_programming_playground.sec11;

import com.rem.reactive_programming_playground.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class Lec01Repeat {

    public static void main(String[] args) {
        //demo4();
        Flux.just(1, 2, 3)
                .repeat(2)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(10);
    }

    private static void demo1() {
        getCountryName()
                .repeat(3)
                .subscribe(Util.subscriber());
    }

    private static void demo2() {
        getCountryName()
                .repeat()
                .takeUntil(s -> s.equalsIgnoreCase("canada"))
                .subscribe(Util.subscriber());
    }

    private static void demo3() {
        var counter = new AtomicInteger(0);
        getCountryName()
                .repeat(() -> counter.incrementAndGet() < 3)
                .subscribe(Util.subscriber());
    }

    private static void demo4() {
        getCountryName()
                .repeatWhen(flux -> flux.delayElements(Duration.ofSeconds(2)).take(2))
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getCountryName() {
        return Mono.fromSupplier(() -> Util.faker().country().name());
    }
}
