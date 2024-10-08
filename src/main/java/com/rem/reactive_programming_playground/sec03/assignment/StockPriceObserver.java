package com.rem.reactive_programming_playground.sec03.assignment;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StockPriceObserver implements Subscriber<Integer> {
    private static final Logger logger = LoggerFactory.getLogger(StockPriceObserver.class);
    private int quantity = 0;
    private int balance = 1000;
    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
        this.subscription = subscription;
    }

    @Override
    public void onNext(Integer price) {
        if (price < 90 && balance >= price) {
            quantity++;
            balance -= price;
            logger.info("bought a stack at {}, total quantity {}, remaining balance {}", price, quantity, balance);
        } else if (price > 110 && quantity > 0) {
            logger.info("Selling {} quantities at {}", quantity, price);
            balance += price * quantity;
            quantity = 0;
            subscription.cancel();
            logger.info("profit: {}", balance - 1000);
        }
    }

    @Override
    public void onError(Throwable throwable) {
        logger.error("error {}", throwable);
    }

    @Override
    public void onComplete() {
        logger.info("completed");
    }
}
