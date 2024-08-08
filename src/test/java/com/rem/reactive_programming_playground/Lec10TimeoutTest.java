package com.rem.reactive_programming_playground;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class Lec10TimeoutTest {

    private Flux<Integer> getItems() {
        return Flux.range(1, 5)
                .delayElements(Duration.ofMillis(200)) // 5 * 200 = 1000
                .log();
    }

    @Test
    public void timeoutTest() {
        StepVerifier.create(getItems())
                .expectNext(1, 2, 3, 4, 5)
                .expectComplete()
                .verify(Duration.ofMillis(1500)); // 500 test fail. 1500 test pass (5 * 200 = 1000 + 500)
    }
}
