package com.rem.reactive_programming_playground.sec04.helper;

import com.rem.reactive_programming_playground.common.Util;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

public class NameGenerator implements Consumer<FluxSink<String>> {

    private FluxSink<String> fluxSink;

    @Override
    public void accept(FluxSink<String> stringFluxSink) {
        this.fluxSink = stringFluxSink;
    }

    public void generate() {
        this.fluxSink.next(Util.faker().name().fullName());
    }
}
