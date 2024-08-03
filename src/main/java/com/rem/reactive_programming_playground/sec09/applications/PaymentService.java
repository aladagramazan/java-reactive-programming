package com.rem.reactive_programming_playground.sec09.applications;

import reactor.core.publisher.Mono;

import java.util.Map;

public class PaymentService {

    private static final Map<Integer, Integer> userBalance = Map.of(
            1, 100,
            2, 200,
            3, 300
    );

   public static Mono<Integer> getUserBalance(Integer userId) {
        return Mono.fromSupplier(() -> userBalance.get(userId));
    }
}
