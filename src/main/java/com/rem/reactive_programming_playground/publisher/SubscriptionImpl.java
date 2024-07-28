package com.rem.reactive_programming_playground.publisher;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubscriptionImpl implements Subscription {

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionImpl.class);
    private static final int MAX_ITEMS = 10;
    private final Faker faker;
    private final Subscriber<? super String> subscriber;
    private boolean isCancelled;
    private int count = 0;

    public SubscriptionImpl(Subscriber<? super String> subscriber) {
        this.subscriber = subscriber;
        this.faker = Faker.instance();
    }

    @Override
    public void request(long requested) {
        if (isCancelled) {
            return;
        }
        logger.info("Requesting: {}", requested);

        if (requested > MAX_ITEMS) {
            subscriber.onError(new IllegalArgumentException("Cannot request more than " + MAX_ITEMS + " items"));
            return;
        }
        for (long i = 0; i < requested && count < MAX_ITEMS; i++) {
            count++;
            subscriber.onNext(this.faker.internet().emailAddress());
        }

        if (count == MAX_ITEMS) {
            logger.info("no more data to produce");
            subscriber.onComplete();
            this.isCancelled = true;
        }
    }

    @Override
    public void cancel() {
        logger.info("Subscription cancelled");
        this.isCancelled = true;
    }
}
