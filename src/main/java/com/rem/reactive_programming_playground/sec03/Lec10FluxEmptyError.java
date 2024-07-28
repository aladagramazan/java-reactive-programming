package com.rem.reactive_programming_playground.sec03;

import com.rem.reactive_programming_playground.common.Util;
import reactor.core.publisher.Flux;

public class Lec10FluxEmptyError {

    public static void main(String[] args) {
        Flux.empty()
                .subscribe(Util.subscriber());

        Flux.error(new RuntimeException("My Error"))
                .subscribe(Util.subscriber());
    }
}
