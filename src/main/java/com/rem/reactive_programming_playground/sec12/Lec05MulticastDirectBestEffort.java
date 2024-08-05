package com.rem.reactive_programming_playground.sec12;

import com.rem.reactive_programming_playground.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Sinks;
import reactor.util.concurrent.Queues;

import java.time.Duration;

public class Lec05MulticastDirectBestEffort {

    private static final Logger log = LoggerFactory.getLogger(Lec05MulticastDirectBestEffort.class);

    //Queues  	Integer.parseInt(System.getProperty("reactor.bufferSize.small", "256")));
    public static void main(String[] args) {
        demo2();

        Util.sleepSeconds(25);
    }

    private static void demo1() {
        // handle through which we would push items
        // onBackpressureBuffer --> bounded queue

        System.setProperty("reactor.bufferSize.small", "16");

        var sink = Sinks.many()
                .multicast()
                .onBackpressureBuffer();

        var flux = sink.asFlux();

        flux.subscribe(Util.subscriber("sam")); // fast consumer
        flux.delayElements(Duration.ofMillis(200)).subscribe(Util.subscriber("mike"));  // slow consumer

        for (int i = 1; i < 100; i++) {
            var result = sink.tryEmitNext(i);
            log.info("item: {}, result: {}", i, result);
        }
    }

    private static void demo2() {
        // handle through which we would push items
        // onBackpressureBuffer --> bounded queue

        System.setProperty("reactor.bufferSize.small", "16");

        var sink = Sinks.many()
                .multicast()
                .directBestEffort(); // if you do not want the slow subsriber affect the fast subscriber

        var flux = sink.asFlux();

        flux.subscribe(Util.subscriber("sam")); // fast consumer
        flux
                .onBackpressureBuffer() // bounded queue  // i am slow, just put in my buffer and i will take it later
                .delayElements(Duration.ofMillis(200)).subscribe(Util.subscriber("mike"));  // slow consumer

        for (int i = 1; i < 100; i++) {
            var result = sink.tryEmitNext(i);
            log.info("item: {}, result: {}", i, result);
        }
    }

}
