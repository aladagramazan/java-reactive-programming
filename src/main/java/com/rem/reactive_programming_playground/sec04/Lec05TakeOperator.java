package com.rem.reactive_programming_playground.sec04;

import com.rem.reactive_programming_playground.common.Util;
import reactor.core.publisher.Flux;

public class Lec05TakeOperator {

    public static void main(String[] args) {
     takeUntil();
    }

    private static void take() {
        Flux.range(1, 10)
                .log("log1")
                .take(3)
                .log("log2")
                .subscribe(Util.subscriber());
    }

    private static void takeWhile() {
        Flux.range(1, 10)
                .log("log1")
                .takeWhile(i -> i < 5) // stop when the condition is not met
                .log("log2")
                .subscribe(Util.subscriber());
    }

    private static void takeUntil() {
        Flux.range(1, 10)
                .log("log1")
                .takeUntil(i -> i > 5) // stop when the condition is met + allow the last item.
                .log("log2")
                .subscribe(Util.subscriber());
    }
}
