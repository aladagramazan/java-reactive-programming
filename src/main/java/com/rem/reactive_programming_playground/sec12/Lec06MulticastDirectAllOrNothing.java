package com.rem.reactive_programming_playground.sec12;

import com.rem.reactive_programming_playground.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Sinks;

import java.time.Duration;

public class Lec06MulticastDirectAllOrNothing {

    private static final Logger log = LoggerFactory.getLogger(Lec06MulticastDirectAllOrNothing.class);
    public static void main(String[] args) {
        demo1();

        Util.sleepSeconds(25);
    }



    private static void demo1() {
        // handle through which we would push items
        // onBackpressureBuffer --> bounded queue

        System.setProperty("reactor.bufferSize.small", "16");

        var sink = Sinks.many()
                .multicast()
                .directAllOrNothing(); // one subscriber is slow and do not deliver to anyone. we are not going to give it to anyone

        var flux = sink.asFlux();

        flux.subscribe(Util.subscriber("sam")); // fast consumer
        flux.delayElements(Duration.ofMillis(200)).subscribe(Util.subscriber("mike"));  // slow consumer

        for (int i = 1; i < 100; i++) {
            var result = sink.tryEmitNext(i);
            log.info("item: {}, result: {}", i, result);
        }
    }
}
