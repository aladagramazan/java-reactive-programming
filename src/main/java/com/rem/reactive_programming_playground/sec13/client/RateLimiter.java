package com.rem.reactive_programming_playground.sec13.client;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class RateLimiter {

    private static final Map<String, Integer> categoryAttempts = Collections.synchronizedMap(new HashMap<>());

    static {
        refreshCategoryAttempts();
    }

    static <T> Mono<T> rateLimit() {
        return Mono.deferContextual(ctx -> {
            var allowCall = ctx.<String>getOrEmpty("category")
                    .map(RateLimiter::canAllow)
                    .orElse(false);
            return allowCall ? Mono.empty() : Mono.error(new RuntimeException("Rate limit exceeded"));
        });
    }


    private static boolean canAllow(String category) {
        var attempts = categoryAttempts.getOrDefault(category, 0);
        if (attempts > 0) {
            categoryAttempts.put(category, attempts - 1);
            return true;
        }
        return false;
    }

    private static void refreshCategoryAttempts() {
       Flux.interval(Duration.ofSeconds(5))
               .startWith(0L)
               .subscribe(i -> {
                   categoryAttempts.put("standard", 2); // 2 requests in 5 seconds
                   categoryAttempts.put("prime", 3); // 3 requests in 5 seconds
               });
    }
}
