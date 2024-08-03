package com.rem.reactive_programming_playground.sec09.applications;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public class UserService {

    private static final Map<String, Integer> users = Map.of(
            "rem", 1,
            "joseph", 2,
            "james", 3
    );

    public static Flux<User> getAllUsers() {
        return Flux.fromIterable(users.entrySet())
                .map(entry -> new User(entry.getValue(), entry.getKey()));
    }

    public static Mono<Integer> getUserId(String username) {
        return Mono.fromSupplier(() -> users.get(username));
    }
}
