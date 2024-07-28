package com.rem.reactive_programming_playground.sec02;


import com.rem.reactive_programming_playground.subscriber.SubscriberImpl;
import reactor.core.publisher.Mono;

public class Lec02MonoJust {

    public static void main(String[] args) {
       var mono = Mono.just("ball");
       var subscriber = new SubscriberImpl();
         mono.subscribe(subscriber);
         subscriber.getSubscription().request(10);
    }
}
