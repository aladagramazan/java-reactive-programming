package com.rem.reactive_programming_playground.sec12;

import com.rem.reactive_programming_playground.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Sinks;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class Lec03SinkThreadSafety {

    private static final Logger log = LoggerFactory.getLogger(Lec03SinkThreadSafety.class);

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

        var list = new ArrayList<>();  // not thread safe

        flux.subscribe(list::add);

        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            CompletableFuture.runAsync(() -> {
                sink.tryEmitNext(finalI);  // not thread safe
            });
        }

        Util.sleepSeconds(3);
        log.info("List size: {}", list.size());
    }

    private static void demo2() {
        // handle through which we would push items
        // onBackpressureBuffer --> unbounded queue

        var sink = Sinks.many()
                .unicast()
                .onBackpressureBuffer();

        var flux = sink.asFlux();

        var list = new ArrayList<>();  // not thread safe

        flux.subscribe(list::add);

        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            CompletableFuture.runAsync(() -> {
                sink.emitNext(finalI,   // THREAD SAFE SINK  -- TRYEMITNEXT IS NOT THREAD SAFE
                        (signalType, emitResult) -> {
                            return Sinks.EmitResult.FAIL_NON_SERIALIZED.equals(emitResult);
                        });
            });
        }

        Util.sleepSeconds(3);
        log.info("List size: {}", list.size());
    }
}
