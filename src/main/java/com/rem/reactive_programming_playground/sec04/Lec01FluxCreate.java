package com.rem.reactive_programming_playground.sec04;

import com.rem.reactive_programming_playground.common.Util;
import reactor.core.publisher.Flux;

public class Lec01FluxCreate {

    public static void main(String[] args) {
        Flux.create(fluxSink -> {
                    String country;
                    do {
                        country = Util.faker().country().name();
                        fluxSink.next(country);
                    } while(!country.equalsIgnoreCase("canada"));
                    fluxSink.complete();
                })
                .subscribe(Util.subscriber());
    }
}
