package com.rem.reactive_programming_playground.sec07;

import com.rem.reactive_programming_playground.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec02SubscribeOn {

    private static final Logger log = LoggerFactory.getLogger(Lec02SubscribeOn.class);

    public static void main(String[] args) {
        var flux = Flux.create(sink -> {
                    for (int i = 1; i < 3; i++) {
                        log.info("generating {}", i);
                        sink.next(i);
                    }
                    sink.complete();
                })
                .doOnNext(v -> log.info("value: {}", v))
                .doFirst(() -> log.info("first doFirst"))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> log.info("second doFirst"));

        Runnable runnable1 = () -> flux.subscribe(Util.subscriber("sub1"));

        Runnable runnable2 = () -> flux.subscribe(Util.subscriber("sub2"));


        Thread thread1 = new Thread(runnable1);
        thread1.start();

        Thread thread2 = new Thread(runnable2);
        thread2.start();

        Util.sleepSeconds(5);
    }

}
