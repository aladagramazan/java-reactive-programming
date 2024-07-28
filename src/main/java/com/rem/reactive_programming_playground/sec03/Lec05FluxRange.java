package com.rem.reactive_programming_playground.sec03;

import com.rem.reactive_programming_playground.common.Util;
import reactor.core.publisher.Flux;

public class Lec05FluxRange {

    public static void main(String[] args) {
        Flux.range(1, 5)
                .subscribe(Util.subscriber());

        Flux.range(1, 5)
                .map(i -> Util.faker().name().fullName())
                .subscribe(Util.subscriber());
    }
}
