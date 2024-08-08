package com.rem.reactive_programming_playground.sec13;

import com.rem.reactive_programming_playground.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class Lec02ContextAppendUpdate {

    private static final Logger log = LoggerFactory.getLogger(Lec02ContextAppendUpdate.class);

    public static void main(String[] args) {
        getWelcomeMessage()
                .contextWrite(context -> context.put("user", context.get("user").toString().toUpperCase()))
                .contextWrite(context -> context.delete("abc"))
                .contextWrite(Context.of("abc", "def").put("hdfs", "hadoop").put("kfeg", "kafka"))
                .contextWrite(Context.of("user", "sam"))
                .subscribe(Util.subscriber());
    }

    private static void append() {
        getWelcomeMessage()
                .contextWrite(Context.of("abc", "def").put("hdfs", "hadoop").put("kfeg", "kafka"))
                .contextWrite(Context.of("user", "sam"))
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getWelcomeMessage() {  // imagine this method is a service call (getMapping)
        return Mono.deferContextual(ctx -> {
            log.info("Context: {}", ctx);
            if (ctx.hasKey("user")) {
                return Mono.just("Welcome to the reactive world %s".formatted(ctx.get("user").toString()));
            }
            return Mono.error(new RuntimeException("Unauthenticated"));
        });
    }

}
