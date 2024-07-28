package com.rem.reactive_programming_playground.sec03;

import com.rem.reactive_programming_playground.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;

public class Lec03FluxFromIterableOrArray {

    public static void main(String[] args) {
        var list = List.of("a", "b", "c");
        var flux = Flux.fromIterable(list);
        flux.subscribe(Util.subscriber());

        Integer [] arr = {1, 2, 3, 4, 5};
        var flux2 = Flux.fromArray(arr);
        flux2.subscribe(Util.subscriber());
    }
}
