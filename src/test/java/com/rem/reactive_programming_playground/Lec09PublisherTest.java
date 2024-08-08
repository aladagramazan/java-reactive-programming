package com.rem.reactive_programming_playground;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

import java.util.function.UnaryOperator;

public class Lec09PublisherTest {

    private UnaryOperator<Flux<String>> processor() {
        return flux -> flux
                .filter(s -> s.length() > 1)
                .map(String::toUpperCase)
                .map(s -> s + ":" + s.length());
    }

    @Test
    public void publisherTest1() {
        var publisher = TestPublisher.<String>create();
        var flux = publisher.flux();

        StepVerifier.create(flux.transform(processor()))
                .then(() -> publisher.emit("a", "ab", "abc"))
                .expectNext("AB:2", "ABC:3")
                .expectComplete()
                .verify();

      /*  StepVerifier.create(processor().apply(flux))
                .expectSubscription()
                .then(() -> publisher.next("a", "ab", "abc"))
                .expectNext("AB:2", "ABC:3")
                .then(() -> publisher.complete())
                .verifyComplete(); */

       // flux.subscribe(Util.subscriber());

       // publisher.next("a", "b", "c");
       // publisher.complete();

       // publisher.emit("a", "b", "c");  // next("a", "b", "c") + complete()
    }

    @Test
    public void publisherTest2() {
        var publisher = TestPublisher.<String>create();
        var flux = publisher.flux();

        StepVerifier.create(flux.transform(processor()))
                .then(() -> publisher.emit("a", "b", "c"))
                .expectComplete()
                .verify();
    }
}
