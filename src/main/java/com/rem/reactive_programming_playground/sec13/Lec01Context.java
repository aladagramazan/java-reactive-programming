package com.rem.reactive_programming_playground.sec13;

import com.rem.reactive_programming_playground.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class Lec01Context {

    private static final Logger log = LoggerFactory.getLogger(Lec01Context.class);

    // Context is an immutable key/value store that follows the reactive streams specification
    public static void main(String[] args) {
        getWelcomeMessage()
                .contextWrite(Context.of("user", "sam"))
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getWelcomeMessage() {  // imagine this method is a service call (getMapping)
        return Mono.deferContextual(ctx -> {
            if (ctx.hasKey("user")) {
                return Mono.just("Welcome to the reactive world %s".formatted(ctx.get("user").toString()));
            }
            return Mono.error(new RuntimeException("Unauthenticated"));
        });
    }
}
