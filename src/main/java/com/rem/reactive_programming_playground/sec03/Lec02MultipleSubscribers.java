package com.rem.reactive_programming_playground.sec03;

import com.rem.reactive_programming_playground.common.Util;
import reactor.core.publisher.Flux;

public class Lec02MultipleSubscribers {

    public static void main(String[] args) {
        var flux = Flux.just(1, 2, 3, 4, 5);
        flux.subscribe(Util.subscriber("sub1"));
        flux
                .filter(i -> i > 10)
                .subscribe(Util.subscriber("sub2"));
        flux
                .filter(i -> i % 2 == 0)
                .map(i -> i + "a")
                .subscribe(Util.subscriber("sub3"));
    }
}
