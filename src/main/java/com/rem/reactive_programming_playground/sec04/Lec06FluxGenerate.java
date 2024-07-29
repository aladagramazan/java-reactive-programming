package com.rem.reactive_programming_playground.sec04;

import com.rem.reactive_programming_playground.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec06FluxGenerate {

    private static final Logger log = LoggerFactory.getLogger(Lec06FluxGenerate.class);

    public static void main(String[] args) {
        log.info("invoke");
        Flux.generate(synchronousSink -> {

                    synchronousSink.next(1);
                    //  synchronousSink.next(2);  // you can emit maximum 1 item per call
                  //  synchronousSink.complete();  // infinite loop (no complete)
                })
                .take(4)  // no complete take 4 items and received 4 items
                .subscribe(Util.subscriber());
    }
}
