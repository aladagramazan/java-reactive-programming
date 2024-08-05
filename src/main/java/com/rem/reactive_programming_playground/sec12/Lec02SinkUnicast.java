package com.rem.reactive_programming_playground.sec12;

import com.rem.reactive_programming_playground.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Sinks;

public class Lec02SinkUnicast {

    private static final Logger log = LoggerFactory.getLogger(Lec02SinkUnicast.class);

    public static void main(String[] args) {
        demo2();
    }

    private static void demo1() {
        // handle through which we would push items
        // onBackpressureBuffer --> unbounded queue

        var sink = Sinks.many()
                .unicast()
                .onBackpressureBuffer();

        var flux = sink.asFlux();

        sink.tryEmitNext("Hello");
        sink.tryEmitNext("Hello2");
        sink.tryEmitNext("Hello3");

        flux.subscribe(Util.subscriber("sam"));
    }

    private static void demo2() {
        // handle through which we would push items
        // onBackpressureBuffer --> unbounded queue

        var sink = Sinks.many()
                .unicast()
                .onBackpressureBuffer();

        var flux = sink.asFlux();

        sink.tryEmitNext("Hello");
        sink.tryEmitNext("Hello2");
        sink.tryEmitNext("Hello3");

        flux.subscribe(Util.subscriber("sam"));
        flux.subscribe(Util.subscriber("mike"));  // Sinks.many().unicast() sinks only allow a single Subscriber
    }

}
