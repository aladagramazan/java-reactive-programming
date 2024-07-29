package com.rem.reactive_programming_playground.sec05;

import com.rem.reactive_programming_playground.common.Util;
import reactor.core.publisher.Flux;

public class Lec02HandleUntilAssignment {

    public static void main(String[] args) {
        Flux.<String>generate(sink -> sink.next(Util.faker().country().name()))
                .handle((country, sink) -> {
                    sink.next(country);
                    if (country.equalsIgnoreCase("canada")) {
                        sink.complete();
                    }
                }).subscribe(Util.subscriber());
    }
}
