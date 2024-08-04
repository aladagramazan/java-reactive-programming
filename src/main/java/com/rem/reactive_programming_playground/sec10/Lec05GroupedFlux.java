package com.rem.reactive_programming_playground.sec10;

import com.rem.reactive_programming_playground.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

public class Lec05GroupedFlux {

    private static final Logger log = LoggerFactory.getLogger(Lec05GroupedFlux.class);

    public static void main(String[] args) {

        Flux.range(1, 30)
                .map(i -> i * 2)
                .startWith(1)
                .groupBy(i -> i % 2)  // 0 or 1
                .flatMap(Lec05GroupedFlux::processEvents)
                .subscribe();

        Util.sleepSeconds(10);

    }

    private static Mono<Void> processEvents(GroupedFlux<Integer, Integer> groupedFlux) {
        log.info("received flux for {}", groupedFlux.key());
        return groupedFlux
                .doOnNext(e -> log.info("key: {}, value: {}", groupedFlux.key(), e))
                .doOnComplete(() -> log.info("key: {} completed", groupedFlux.key()))
                .then();
    }
}
