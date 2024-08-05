package com.rem.reactive_programming_playground.sec12;

import com.rem.reactive_programming_playground.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Sinks;

public class Lec01Sink {

    private static final Logger log = LoggerFactory.getLogger(Lec01Sink.class);

    public static void main(String[] args) {
        demo3();
    }

    private static void demo1() {
        var sink = Sinks.one();
        var mono = sink.asMono();

        mono.subscribe(Util.subscriber());

        // sink.tryEmitValue("Hello");
        //  sink.tryEmitEmpty();
        sink.tryEmitError(new RuntimeException("Oops"));
    }

    private static void demo2() {
        var sink = Sinks.one();
        var mono = sink.asMono();
        sink.tryEmitValue("Hello");
        mono.subscribe(Util.subscriber("rem"));
        mono.subscribe(Util.subscriber("rem2"));
    }

    private static void demo3() {
        var sink = Sinks.one();
        var mono = sink.asMono();
        mono.subscribe(Util.subscriber("rem"));

        sink.emitValue("Hello", (signalType, emitResult) -> {
            log.info("Hello");
            log.info("Signal Type: {}", signalType);
            log.info("Emit Result: {}", emitResult.name());
            return false; // not retry (true for retry)
        });

        sink.emitValue("Hi", (signalType, emitResult) -> {
            log.info("Hi");
            log.info("Signal Type: {}", signalType);
            log.info("Emit Result: {}", emitResult.name());
            return false;  // not retry // true for retry
        });
    }
}
