package com.rem.reactive_programming_playground.sec12;

import com.rem.reactive_programming_playground.common.Util;
import reactor.core.publisher.Sinks;

public class Lec07Replay {

    //late subscribers can see the past messages actually

    public static void main(String[] args) {
        demo1();
    }



    private static void demo1() {
        var sink = Sinks.many()
                .replay()
                .all();  // replay all the messages
        //   .limit(1); // replay only the last message

        var flux = sink.asFlux();

        flux.subscribe(Util.subscriber("sam"));
        flux.subscribe(Util.subscriber("mike"));

        sink.tryEmitNext("Hello");
        sink.tryEmitNext("Hello2");
        sink.tryEmitNext("Hello3");

        Util.sleepSeconds(2);

        flux.subscribe(Util.subscriber("rem"));

        sink.tryEmitNext("new message");
    }
}
