package com.rem.reactive_programming_playground.sec08;

import com.rem.reactive_programming_playground.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class Lec05BackPressureStrategies {

    private static final Logger log = LoggerFactory.getLogger(Lec05BackPressureStrategies.class);

    public static void main(String[] args) {

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
                //.onBackpressureBuffer()  // buffer producer'ın ürettiği itemları memoryde tutar.
               // .onBackpressureError()
               // .onBackpressureBuffer(10)
               // .onBackpressureDrop(i -> log.info("Dropped: {}", i))
                .onBackpressureLatest()
                .log()
                .limitRate(1) // burada eklersem bile, producer 500 item üretir ve consumer birer birer item alır.
                .publishOn(Schedulers.boundedElastic()) // consume on boundedElastic thread
                .map(Lec05BackPressureStrategies::timeConsumingProcess)
                .subscribe();

        Util.sleepSeconds(60);
    }

    private static int timeConsumingProcess(int i) {
        log.info("received: {}", i);
        Util.sleepSeconds(1);
        return i;
    }
}
