package com.rem.reactive_programming_playground.sec13.client;

import reactor.util.context.Context;

import java.util.Map;
import java.util.function.Function;

public class UserService {

    private static final Map<String, String> userMap = Map.of(
            "sam", "standard",  // 2 requests in 5 seconds
            "mike", "prime"  // 3 requests in 5 seconds
    );

    static Function<Context, Context> userCategoryContext() {
        return context -> context.<String>getOrEmpty("user")
                .filter(userMap::containsKey)
                .map(userMap::get)
                .map(category -> context.put("category", category))
                .orElse(Context.empty());
    }
}
