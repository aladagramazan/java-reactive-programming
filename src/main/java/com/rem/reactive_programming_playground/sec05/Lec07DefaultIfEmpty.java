package com.rem.reactive_programming_playground.sec05;

import com.rem.reactive_programming_playground.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec07DefaultIfEmpty {

    public static void main(String[] args) {
        Flux.range(1, 10)
                .filter(i -> i > 10)
                .defaultIfEmpty(50)
                .subscribe(Util.subscriber());
    }
}
