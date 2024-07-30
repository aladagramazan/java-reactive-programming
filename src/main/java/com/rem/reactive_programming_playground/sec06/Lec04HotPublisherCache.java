package com.rem.reactive_programming_playground.sec06;

import com.rem.reactive_programming_playground.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec04HotPublisherCache {

    private static final Logger log = LoggerFactory.getLogger(Lec04HotPublisherCache.class);

    public static void main(String[] args) {
        var movieFlux = stockPrice()   // cold publisher like as Netflix
                //   .share(); // if I add share() method, it will become hot publisher like as TV
                //.publish().autoConnect(0);  // going on do not wait for any subscriber to join
                        .replay(1).autoConnect(0); // .replay(1) see the latest price

        // use autoConnect(0) --> it will start producing even 0 subscribers once it started

        Util.sleepSeconds(4);

        log.info("sam joining");

        movieFlux
                .subscribe(Util.subscriber("sam"));

        Util.sleepSeconds(4);

        log.info("mike joining");

        movieFlux
                .subscribe(Util.subscriber("mike"));

        Util.sleepSeconds(15);
    }

    private static Flux<Integer> stockPrice() {
        return Flux.generate(sink -> sink.next(Util.faker().random().nextInt(10, 200)))
                .delayElements(Duration.ofSeconds(3))
                .doOnNext(price -> log.info("emitting price: {}", price))
                .cast(Integer.class);
    }
}
