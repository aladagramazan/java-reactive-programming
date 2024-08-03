package com.rem.reactive_programming_playground.common;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Objects;
import java.util.function.UnaryOperator;

public class Util {

    private static final Logger log = LoggerFactory.getLogger(Util.class);

    private static final Faker faker = Faker.instance();

    public static <T> Subscriber<T> subscriber() {
        return new DefaultSubscriber<>("");
    }
    public static <T> Subscriber<T> subscriber(String name) {
        return new DefaultSubscriber<>(name);
    }

    public static Faker faker() {
        return faker;
    }

    public static void sleepSeconds(int seconds) {
        try {
            Thread.sleep(Duration.ofSeconds(seconds).toMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleep(Duration duration) {
        try {
            Thread.sleep(duration.toMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        var mono = Mono.just(1);
        mono.subscribe(subscriber("sub1"));
        mono.subscribe(subscriber("sub2"));
    }

    public static <T> UnaryOperator<Flux<T>> fluxLogger(String name) {
        return flux -> flux
                .doOnSubscribe(s -> log.info("subscribed to {}", name))
                .doOnCancel(() -> log.info("canceled for {}", name))
                .doOnComplete(() -> log.info("completed for {}", name));
    }


}
