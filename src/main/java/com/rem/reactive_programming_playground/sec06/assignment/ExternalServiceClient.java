package com.rem.reactive_programming_playground.sec06.assignment;

import com.rem.reactive_programming_playground.common.AbstractHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.Objects;

public class ExternalServiceClient extends AbstractHttpClient {

    private static final Logger log = LoggerFactory.getLogger(ExternalServiceClient.class);
    private Flux<Order> orderFlux;

    public Flux<Order> orderStream() {
        if (Objects.isNull(orderFlux)) {
            this.orderFlux = getOrderStream();
        }

        return this.orderFlux;
    }

    public Flux<Order> getOrderStream() {
        return this.client.get()
                .uri("/demo04/orders/stream")
                .responseContent()
                .asString()
                .map(this::parseOrder)
                .doOnNext(order -> log.info("nnfekfe{}", order))
                .publish()
                .refCount(2);
    }

    private Order parseOrder(String order) {
        var arr = order.split(":");
        return new Order(arr[0],
                arr[1],
                Integer.parseInt(arr[2]),
                Integer.parseInt(arr[3]));
    }
}
