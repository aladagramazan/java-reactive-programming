package com.rem.reactive_programming_playground.sec04;

import com.rem.reactive_programming_playground.common.Util;
import com.rem.reactive_programming_playground.sec04.helper.NameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;

public class Lec03FluxSinkThreadSafety {

    private static final Logger log = LoggerFactory.getLogger(Lec03FluxSinkThreadSafety.class);

    public static void main(String[] args) {
        demo2();
    }

    private static void demo1() {
        var list = new ArrayList<Integer>();
        Runnable producer = () -> {
            for (int i = 0; i < 1000; i++) {
                list.add(i);
            }
        };

        for (int i = 0; i < 10; i++) {
            new Thread(producer).start();
        }
        Util.sleepSeconds(3);
        log.info("size: {}", list.size());
    }

    private static void demo2() {
        var list = new ArrayList<String>();
        var generator = new NameGenerator();
        var flux = Flux.create(generator);
        flux.subscribe(list::add);

        Runnable producer = () -> {
            for (int i = 0; i < 1000; i++) {
                generator.generate();
            }
        };

        for (int i = 0; i < 10; i++) {
            new Thread(producer).start();
        }
        Util.sleepSeconds(3);
        log.info("size: {}", list.size());
    }

    // arraylist is not thread safe
    // sink is thread safe
}
