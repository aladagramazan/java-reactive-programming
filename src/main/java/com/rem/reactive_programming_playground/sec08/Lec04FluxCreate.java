package com.rem.reactive_programming_playground.sec08;

import com.rem.reactive_programming_playground.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class Lec04FluxCreate {

    // Flux --> generate back pressure handling automatically
    // Flux --> create not back pressure handling automatically

    private static final Logger log = LoggerFactory.getLogger(Lec04FluxCreate.class);

    public static void main(String[] args) {

        System.setProperty("reactor.bufferSize.small", "16");

        var publisher = Flux.create(fluxSink -> {
                    for (int i = 1; i < 500 && !fluxSink.isCancelled(); i++) {
                        log.info("generating: {}", i);
                        fluxSink.next(i);
                        Util.sleep(Duration.ofMillis(50));
                    }
                    fluxSink.complete();
                }).cast(Integer.class)
                .subscribeOn(Schedulers.parallel());  // generate on parallel thread

        // 500 item produce edilmeden consumer çalışmaz. limitRate(1) yapsam da.

        publisher
                .limitRate(1) // burada eklersem bile, producer 500 item üretir ve consumer birer birer item alır.
                .publishOn(Schedulers.boundedElastic()) // consume on boundedElastic thread
                .map(Lec04FluxCreate::timeConsumingProcess)
                .subscribe();

        Util.sleepSeconds(60);
    }

    private static int timeConsumingProcess(int i) {
        log.info("received: {}", i);
        Util.sleepSeconds(1);
        return i;
    }
}
