package com.rem.reactive_programming_playground.sec09.helper;

import com.rem.reactive_programming_playground.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class NameGenerator {

    private static final Logger log = LoggerFactory.getLogger(NameGenerator.class);
    private static List<String> redis = new ArrayList<>();

    public Flux<String> generateNames() {
        return Flux.generate(sink -> {
                    log.info("generated name");
                    Util.sleepSeconds(1);
                    var name = Util.faker().name().firstName();
                    redis.add(name);
                    sink.next(name);
                })
                .startWith(redis)  // cashed data
                .cast(String.class);
    }
}
