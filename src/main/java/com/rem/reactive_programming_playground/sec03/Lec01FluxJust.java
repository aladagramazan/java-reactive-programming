package com.rem.reactive_programming_playground.sec03;

import com.rem.reactive_programming_playground.common.Util;
import reactor.core.publisher.Flux;

public class Lec01FluxJust {

    public static void main(String[] args) {
        Flux.just(1, 2, "rem")
                .subscribe(Util.subscriber());
    }
}
