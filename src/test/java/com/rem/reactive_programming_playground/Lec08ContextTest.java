package com.rem.reactive_programming_playground;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;
import reactor.util.context.Context;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Lec08ContextTest {

    Mono<String> getWelcomeMessage() {  // imagine this method is a service call (getMapping)
        return Mono.deferContextual(ctx -> {
            if (ctx.hasKey("user")) {
                return Mono.just("Welcome to the reactive world %s".formatted(ctx.get("user").toString()));
            }
            return Mono.error(new RuntimeException("Unauthenticated"));
        });
    }

    @Test
    public void welcomeMessageTest() {
        var options = StepVerifierOptions.create().withInitialContext(Context.of("user", "sam"));
        StepVerifier.create(getWelcomeMessage(), options)
                .expectNext("Welcome to the reactive world sam")
                .expectComplete()
                .verify();
    }

    @Test
    public void unauthenticatedTest() {
        var options = StepVerifierOptions.create().withInitialContext(Context.empty());
        StepVerifier.create(getWelcomeMessage(), options)
                .consumeErrorWith(ex -> {
                    assertEquals(RuntimeException.class, ex.getClass());
                    assertEquals("Unauthenticated", ex.getMessage());
                })
                .verify();
    }
}
