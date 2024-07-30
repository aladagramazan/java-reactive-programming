package com.rem.reactive_programming_playground.sec06;

import com.rem.reactive_programming_playground.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;
public class Lec02HotPublisher {

    private static final Logger log = LoggerFactory.getLogger(Lec02HotPublisher.class);

    public static void main(String[] args) {
        var movieFlux = movieStream()   // cold publisher like as Netflix
                .share(); // if I add share() method, it will become hot publisher like as TV

        Util.sleepSeconds(2);

        movieFlux
                .take(4)
                .subscribe(Util.subscriber("sam"));

        Util.sleepSeconds(5);

        movieFlux
                .take(3)
                .subscribe(Util.subscriber("mike"));

        Util.sleepSeconds(15);
    }

    private static Flux<String> movieStream() {
        return Flux.generate(
                        () -> {
                            log.info("received the request");
                            return 1;
                        },
                        (state, sink) -> {
                            var scene = "movie scene " + state;
                            log.info("playing: {}", scene);
                            sink.next(scene);
                            return ++state;
                        }
                )
                .take(10)
                .delayElements(Duration.ofSeconds(1))
                .cast(String.class);
    }

}
