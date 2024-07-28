package com.rem.reactive_programming_playground.sec02;

import com.rem.reactive_programming_playground.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class Lec08PublisherCreateVsExecution {
    private static final Logger logger = LoggerFactory.getLogger(Lec08PublisherCreateVsExecution.class);

    public static void main(String[] args) {
        getName()
                .subscribe(Util.subscriber());

    }

    private static Mono<String> getName() {
        logger.info("entered the method...");
        return Mono.fromSupplier(() -> {
            logger.info("Generating name...");
            Util.sleepSeconds(3);
            return Util.faker().name().firstName();
        });
    }
}
