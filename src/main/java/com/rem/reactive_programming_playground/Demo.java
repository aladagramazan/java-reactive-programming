package com.rem.reactive_programming_playground;

import com.rem.reactive_programming_playground.publisher.PublisherImpl;
import com.rem.reactive_programming_playground.subscriber.SubscriberImpl;

import java.time.Duration;

public class Demo {

    public static void main(String[] args) throws InterruptedException {
        demo4();
    }

    public static void demo1() {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
    }

    public static void demo2() throws InterruptedException {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2).toMillis());
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2).toMillis());
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2).toMillis());
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2).toMillis());
        subscriber.getSubscription().request(3);
    }

    public static void demo3() throws InterruptedException {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2).toMillis());
        subscriber.getSubscription().cancel();
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2).toMillis());
    }

    public static void demo4() throws InterruptedException {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2).toMillis());
        subscriber.getSubscription().request(11);
        Thread.sleep(Duration.ofSeconds(2).toMillis());
        subscriber.getSubscription().request(3);
    }
}
