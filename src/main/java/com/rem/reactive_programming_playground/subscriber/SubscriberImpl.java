package com.rem.reactive_programming_playground.subscriber;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubscriberImpl implements Subscriber<String> {

    private static final Logger logger = LoggerFactory.getLogger(SubscriberImpl.class);
    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    @Override
    public void onNext(String email) {
        logger.info("Received: {}", email);
    }

    @Override
    public void onError(Throwable t) {
        logger.error("Error: {}", t);
    }

    @Override
    public void onComplete() {
        logger.info("Completed");
    }
}
