package com.rem.reactive_programming_playground.sec09.client;

import com.rem.reactive_programming_playground.common.AbstractHttpClient;
import com.rem.reactive_programming_playground.sec09.assignment.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class ExternalServiceClient extends AbstractHttpClient {

    public Mono<Product> getProduct(int productId) {
        return Mono.zip(
                        this.getProductName(productId),
                        this.getReview(productId),
                        this.getPrice(productId)
                )
                .map(tuple -> new Product(tuple.getT1(), tuple.getT2(), tuple.getT3()));
    }

    public Mono<String> getProductName(int productId) {
        return get("/demo05/product/" + productId);
    }

    public Mono<String> getReview(int productId) {
        return get("/demo05/product/" + productId);
    }

    public Mono<String> getPrice(int productId) {
        return get("/demo05/product/" + productId);
    }

    public Mono<String> get(String path) {
        return this.client.get()
                .uri(path)
                .responseContent()
                .asString()
                .next();
    }
}
