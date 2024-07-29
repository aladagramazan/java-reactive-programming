package com.rem.reactive_programming_playground.sec05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec05Subscribe {
    private static final Logger log = LoggerFactory.getLogger(Lec05Subscribe.class);

    public static void main(String[] args) {
        Flux.range(1, 10)
                .doOnNext(i -> log.info("processing: {}", i))
                .doOnComplete(() -> log.info("done"))
                .doOnError(err -> log.error("error: {}", err.getMessage()))
                .subscribe();
    }
}
