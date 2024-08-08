package com.rem.reactive_programming_playground;

import com.rem.reactive_programming_playground.common.Util;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Lec04RangeTest {

    private Flux<Integer> getItems() {
        return Flux.range(1, 200)
                .log();
    }

    private Flux<Integer> getRandomItems() {
        return Flux.range(1, 200)
                .map(i -> Util.faker().random().nextInt(1, 100))
                .log();
    }

    @Test
    public void rangeTest1() {
        StepVerifier.create(getItems())
                .expectNext(1, 2, 3, 4)
                .expectNextCount(196)
                .expectComplete()
                .verify();
    }

    @Test
    public void rangeTest2() {
        StepVerifier.create(getItems())
                .expectNext(1, 2, 3, 4)
                .expectNextCount(194)
                .expectNext(199, 200)
                .expectComplete()
                .verify();
    }

    @Test
    public void rangeTest3() {
        StepVerifier.create(getRandomItems())
                .expectNextMatches(i -> i > 0 && i < 101)
                .expectNextCount(199)
                .expectComplete()
                .verify();
    }

    @Test
    public void rangeTest4() {
        StepVerifier.create(getRandomItems())
                .thenConsumeWhile(i -> i > 0 && i < 101)
                .expectComplete()
                .verify();
    }
}
