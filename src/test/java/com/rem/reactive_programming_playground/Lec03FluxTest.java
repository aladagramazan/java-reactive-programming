package com.rem.reactive_programming_playground;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Lec03FluxTest {

    private Flux<Integer> getItems() {
        return Flux.just(1, 2, 3, 4)
                .log();
    }

    @Test
    public void fluxTest1() {
        StepVerifier.create(getItems(), 1)
                .expectNext(1)
                //.expectComplete()  // expecting complete signal but not getting it and test hanging
                .thenCancel()  // cancel the subscription, not use expectComplete(), use thenCancel()
                .verify();
    }

    @Test
    public void fluxTest() {
        Flux<Integer> flux = getItems();
        StepVerifier.create(flux)
                .expectNext(1, 2, 3, 4)
                .expectComplete()
                .verify();
    }


}
