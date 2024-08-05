package com.rem.reactive_programming_playground.sec11;

import com.rem.reactive_programming_playground.common.Util;
import com.rem.reactive_programming_playground.sec09.client.ServerError;
import com.rem.reactive_programming_playground.sec11.client.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

import java.time.Duration;

public class Lec03ExternalServiceDemo {

    private static final Logger log = LoggerFactory.getLogger(Lec03ExternalServiceDemo.class);

    public static void main(String[] args) {
        Flux.just("a")
                .retry(1)
                .retry(2)
                .subscribe(Util.subscriber());
    }

    private static void repeat() {
        var client = new ExternalServiceClient();
        client.getCountry()
                .repeat()
                .takeUntil(s -> s.equalsIgnoreCase("canada"))
                .subscribe(Util.subscriber());
    }

    private static void retry() {
        var client = new ExternalServiceClient();
        client.getProductName(2)
                .retryWhen(retryOnServerError())
                .subscribe(Util.subscriber());
    }

    private static Retry retryOnServerError() {
        return Retry.fixedDelay(20, Duration.ofSeconds(1))
                .filter(err -> err instanceof ServerError)
                .doBeforeRetry(rs -> log.info("retrying {}", rs.failure().getMessage()))
                .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) -> retrySignal.failure());
    }
}
