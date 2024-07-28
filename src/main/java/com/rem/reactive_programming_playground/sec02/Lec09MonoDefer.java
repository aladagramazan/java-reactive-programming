package com.rem.reactive_programming_playground.sec02;

import com.rem.reactive_programming_playground.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec09MonoDefer {
    private static final Logger logger = LoggerFactory.getLogger(Lec09MonoDefer.class);

    public static void main(String[] args) {
       Mono.defer(Lec09MonoDefer::createPublisher).
               subscribe(Util.subscriber());
    }

    private static Mono<Integer> createPublisher() {
        logger.info("Entered the createPublisher method");
        var list = List.of(1, 2, 3, 4, 5);
        Util.sleepSeconds(1);
        return Mono.fromSupplier(() -> sum(list));
    }

    private static int sum(List<Integer> list) {
        logger.info("finding sum of list: {}", list);
        Util.sleepSeconds(3);
        return list.stream()
                .mapToInt(i -> i)
                .sum();
    }
}
