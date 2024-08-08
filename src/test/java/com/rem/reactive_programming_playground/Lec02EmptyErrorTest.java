package com.rem.reactive_programming_playground;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lec02EmptyErrorTest {
    private static Mono<String> getUserName(int userId) {
        return switch (userId) {
            case 1 -> Mono.just("john");
            case 2 -> Mono.empty();
            default -> Mono.error(new RuntimeException("Not found"));
        };
    }

    @Test
    public void getUserName() {
        StepVerifier.create(getUserName(1))
                .expectNext("john")
                .expectComplete()
                .verify();  // subscribe
    }

    @Test
    public void getUserNameEmpty() {
        StepVerifier.create(getUserName(2))
                .expectComplete()
                .verify();  // subscribe
    }

    @Test
    public void getUserNameError() {
        StepVerifier.create(getUserName(3))
                .consumeErrorWith(ex -> {
                    Assertions.assertEquals(RuntimeException.class, ex.getClass());
                    Assertions.assertEquals("Not found", ex.getMessage());
                })
             //   .expectErrorMessage("Not found")
                .verify();  // subscribe
    }
}
