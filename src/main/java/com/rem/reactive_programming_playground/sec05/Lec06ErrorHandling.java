package com.rem.reactive_programming_playground.sec05;

import com.rem.reactive_programming_playground.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec06ErrorHandling {
    private static final Logger log = LoggerFactory.getLogger(Lec06ErrorHandling.class);

    public static void main(String[] args) {
         Flux.range(1, 10)
                 .map(i -> 10 / (5 - i))
                 .onErrorContinue((err, obj) -> {
                     log.error("error: {}", err.getMessage());
                     log.info("object: {}", obj);
                 })
                .subscribe(Util.subscriber());


        // Mono.error(new RuntimeException("oops"))
             //   .onErrorComplete()
             //   .onErrorResume(ArithmeticException.class, ex -> fallback1())
             //   .onErrorResume(ex -> fallback2())
             //   .onErrorReturn(-1)
           //     .subscribe(Util.subscriber());
    }
    private static void onErrorReturn() {
        Mono.just(5)
                .map(i -> 10 / (5 - i))
                // .onErrorReturn(-1) // fallback value is -1
                .onErrorReturn(IllegalArgumentException.class, -1)
                .onErrorReturn(ArithmeticException.class, -2)
                .subscribe(Util.subscriber());
    }

    private static Mono<Integer> fallback1() {
        return Mono.fromSupplier(() -> Util.faker().random().nextInt(10, 200));
    }

    private static Mono<Integer> fallback2() {
        return Mono.fromSupplier(() -> Util.faker().random().nextInt(100, 500));
    }


}
