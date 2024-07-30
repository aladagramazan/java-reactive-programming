package com.rem.reactive_programming_playground.sec06.assignment;

import reactor.core.publisher.Flux;

public interface OrderProcessor {

    void consume(Order order);

    Flux<String> stream();
}
