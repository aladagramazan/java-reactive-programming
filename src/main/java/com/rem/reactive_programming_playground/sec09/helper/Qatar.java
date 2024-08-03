package com.rem.reactive_programming_playground.sec09.helper;

import com.rem.reactive_programming_playground.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Qatar {

    private static final String AIRLINE = "Qatar";

    public static Flux<Flight> getFlights() {
        return Flux.range(1, Util.faker().random().nextInt(1, 6))
                .delayElements(Duration.ofMillis(Util.faker().random().nextInt(300, 800)))
                .map(i -> new Flight(AIRLINE, Util.faker().random().nextInt(400, 900)))
                .transform(Util.fluxLogger(AIRLINE));
    }
}
