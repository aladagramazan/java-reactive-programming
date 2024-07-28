package com.rem.reactive_programming_playground.sec02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class Lec03MonoSubscribe {
    private static final Logger logger = LoggerFactory.getLogger(Lec03MonoSubscribe.class);

    public static void main(String[] args) {
        var mono = Mono.just(1);
        mono.subscribe(i -> logger.info("Received: {}", i),
                err -> logger.error("Error: {}", err.getMessage()),
                () -> logger.info("Completed"),
                subscription -> subscription.request(1));
    }
}
