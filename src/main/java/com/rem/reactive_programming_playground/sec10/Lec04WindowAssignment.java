package com.rem.reactive_programming_playground.sec10;

import com.rem.reactive_programming_playground.common.Util;
import com.rem.reactive_programming_playground.sec10.assignment.window.FileWriter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.file.Path;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class Lec04WindowAssignment {
    public static void main(String[] args) {
        var counter = new AtomicInteger(0);
        var fileNameFormat = "/Users/ramazanaladag/IdeaProjects/reactive-programming-playground/src/main/resources/sec10/file%d.txt";
        eventStream()
                .window(Duration.ofSeconds(1))
                .flatMap(flux -> FileWriter.create(flux, Path.of(fileNameFormat.formatted(counter.incrementAndGet()))))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(20);
    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(500))
                .map(i -> "event-" + (i + 1));
    }

    private static Mono<Void> processEvents(Flux<String> flux) {
        return flux.doOnNext(e -> System.out.print("*"))
                .doOnComplete(System.out::println)
                .then();
    }
}

