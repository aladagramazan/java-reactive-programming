package com.rem.reactive_programming_playground.sec06;

import com.rem.reactive_programming_playground.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec01ColdPublisher {

    private static final Logger log = LoggerFactory.getLogger(Lec01ColdPublisher.class);

    public static void main(String[] args) {
        var flux = Flux.create(fluxSink -> {
            log.info("invoked");
            for (int i = 0; i < 2; i++) {
                fluxSink.next(i);
            }
            fluxSink.complete();
        });

        flux.subscribe(Util.subscriber("sub1"));
        flux.subscribe(Util.subscriber("sub2"));


    }
}
