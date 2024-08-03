package com.rem.reactive_programming_playground.sec09;

import com.rem.reactive_programming_playground.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec14CollectionList {

    public static void main(String[] args) {
        Flux.range(1, 10)
                .concatWith(Mono.error(new RuntimeException("my error")))
                .collectList()
                .subscribe(Util.subscriber());
    }
}
