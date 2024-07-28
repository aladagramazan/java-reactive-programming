package com.rem.reactive_programming_playground.sec02;

import com.rem.reactive_programming_playground.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec05MonoFromSupplier {

    private static final Logger logger = LoggerFactory.getLogger(Lec05MonoFromSupplier.class);

    public static void main(String[] args) {
        var list = List.of(1, 2, 3, 4, 5);
        Mono.fromSupplier(() -> sum(list))
                .subscribe(Util.subscriber());
    }

    public static int sum(List<Integer> list) {
        logger.info("finding sum of list: {}", list);
        return list.stream()
                .mapToInt(i -> i)
                .sum();
    }
}
