package com.rem.reactive_programming_playground.sec02;

import com.rem.reactive_programming_playground.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class Lec06MonoFromRunnable {

    private static final Logger logger = LoggerFactory.getLogger(Lec06MonoFromRunnable.class);

    public static void main(String[] args) {
        getProductName(2)
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getProductName(int productId) {
        if (productId == 1) {
            return Mono.fromSupplier(() -> Util.faker().commerce().productName());
        }
        return Mono.fromRunnable(() -> notifyBusiness(productId));
    }

    public static void notifyBusiness(int productId) {
        logger.info("Notify that the product with id {} is out of stock", productId);
    }
}
