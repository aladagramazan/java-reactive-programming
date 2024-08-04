package com.rem.reactive_programming_playground.sec11;

import com.rem.reactive_programming_playground.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class Lec02Retry {

    private static final Logger log = LoggerFactory.getLogger(Lec02Retry.class);

    public static void main(String[] args) {
        demo2();

        Util.sleepSeconds(10);
    }

    private static void demo1() {
        getCountryName()
                .retry(2)
                .subscribe(Util.subscriber());
    }

    private static void demo2() {
        getCountryName()
                .retryWhen(
                        Retry.fixedDelay(3, Duration.ofSeconds(2))
                                .filter(err -> err instanceof RuntimeException)
                                .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) -> retrySignal.failure())
                )
                .subscribe(Util.subscriber());
    }


    private static Mono<String> getCountryName() {
        var counter = new AtomicInteger(0);
        return Mono.fromSupplier(() -> {
                    if (counter.incrementAndGet() < 5) {
                        throw new RuntimeException("some error");
                    }
                    return Util.faker().country().name();
                })
                .doOnError(err -> log.error("Error: ", err.getMessage()))
                .doOnSubscribe(s -> log.info("Subscribing"));
    }
}
