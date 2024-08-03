package com.rem.reactive_programming_playground.sec09;

import com.rem.reactive_programming_playground.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec07Zip {

    record Car(String body, String engine, String tires) {}

    public static void main(String[] args) {
        Flux.zip(getBody(), getEngine(), getTires())
                .map(tuple -> new Car(tuple.getT1(), tuple.getT2(), tuple.getT3()))
                .subscribe(Util.subscriber()
                );

        Util.sleepSeconds(5);
    }



    private static Flux<String> getBody() {
        return Flux.range(1, 5)
                .map(i -> "body_" + i)
                .delayElements(Duration.ofMillis(100));
    }

    private static Flux<String> getEngine() {
        return Flux.range(1, 1)
                .map(i -> "engine_" + i)
                .delayElements(Duration.ofMillis(200));
    }

    private static Flux<String> getTires() {
        return Flux.range(1, 10)
                .map(i -> "tires_" + i)
                .delayElements(Duration.ofMillis(75));
    }
}
