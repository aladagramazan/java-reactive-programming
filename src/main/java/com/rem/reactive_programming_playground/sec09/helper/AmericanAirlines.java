package com.rem.reactive_programming_playground.sec09.helper;

import com.rem.reactive_programming_playground.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class AmericanAirlines {

    private static final String AIRLINE = "American Airlines";

    public static Flux<Flight> getFlights() {
        return Flux.range(1, Util.faker().random().nextInt(3, 9))
                .delayElements(Duration.ofMillis(Util.faker().random().nextInt(400, 700)))
                .map(i -> new Flight(AIRLINE, Util.faker().random().nextInt(500, 1100)))
                .transform(Util.fluxLogger(AIRLINE));
    }
}
