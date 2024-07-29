package com.rem.reactive_programming_playground.sec05;

import com.rem.reactive_programming_playground.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec04Delay {

    public static void main(String[] args) {
        Flux.range(1, 10)
                .log()
                .delayElements(Duration.ofSeconds(1)) // producer gives data one by one with 1 second delay to subscribe
                .subscribe(Util.subscriber());

        Util.sleepSeconds(12);
    }
}
