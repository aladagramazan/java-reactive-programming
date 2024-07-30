package com.rem.reactive_programming_playground.sec07;

import com.rem.reactive_programming_playground.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec07Parallel {

    private static final Logger log = LoggerFactory.getLogger(Lec07Parallel.class);

    public static void main(String[] args) {
        Flux.range(1, 10)
                .parallel(3)
                .runOn(Schedulers.parallel())
                .map(Lec07Parallel::process)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(20);
    }

    private static int process(int i) {
        log.info("time consuming task: {}", i);
        Util.sleepSeconds(1);
        return i * 2;
    }



}
