package com.rem.reactive_programming_playground.sec02;

import com.rem.reactive_programming_playground.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public class Lec07MonoFromFuture {

    private static final Logger logger = LoggerFactory.getLogger(Lec07MonoFromFuture.class);

    public static void main(String[] args) throws InterruptedException {
        Mono.fromFuture(Lec07MonoFromFuture::getName)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(1);
    }

    private static CompletableFuture<String> getName() {
        logger.info("generating name...");
        return CompletableFuture.supplyAsync(() -> Util.faker().name().firstName());
    }

}
