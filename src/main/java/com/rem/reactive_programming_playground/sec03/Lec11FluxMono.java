package com.rem.reactive_programming_playground.sec03;

import com.rem.reactive_programming_playground.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec11FluxMono {

    public static void main(String[] args) {
        var flux = Flux.range(1, 10);
        Mono.from(flux)
                .subscribe(Util.subscriber());
    }

    public static void monoToFlux() {
        var mono = getUsername(1);
        save(Flux.from(mono));
    }

    private static Mono<String> getUsername(int userId) {
       return switch (userId) {
            case 1 -> Mono.just("john");
            case 2 -> Mono.empty();
            default -> Mono.error(new RuntimeException("Not Found"));
        };
    }

    public static void save(Flux<String> flux) {
        flux.subscribe(Util.subscriber());
    }
}
