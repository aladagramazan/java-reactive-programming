package com.rem.reactive_programming_playground.sec12;

import com.rem.reactive_programming_playground.common.Util;
import reactor.core.publisher.Sinks;

import java.util.Queue;

public class Lec04Multicast {

    public static void main(String[] args) {
        demo2();
    }


    private static void demo1() {
        // handle through which we would push items
        // onBackpressureBuffer --> bounded queue

        // 256 items --> system queue

        var sink = Sinks.many()
                .multicast()
                .onBackpressureBuffer();

        var flux = sink.asFlux();

        flux.subscribe(Util.subscriber("sam"));
        flux.subscribe(Util.subscriber("mike"));

        sink.tryEmitNext("Hello");
        sink.tryEmitNext("Hello2");
        sink.tryEmitNext("Hello3");

        Util.sleepSeconds(2);

        flux.subscribe(Util.subscriber("rem"));  // just see the last item as new message
        // late consumers will only see the new messages

        sink.tryEmitNext("new message");
    }




    //warmup
    private static void demo2() {
        // handle through which we would push items
        // onBackpressureBuffer --> bounded queue

        // 256 items --> system queue

        var sink = Sinks.many()
                .multicast()
                .onBackpressureBuffer();

        var flux = sink.asFlux();

        sink.tryEmitNext("Hello");
        sink.tryEmitNext("Hello2");  // items on buffer queue if no subscriber
        sink.tryEmitNext("Hello3");

        Util.sleepSeconds(2);

        flux.subscribe(Util.subscriber("sam")); // same time  // sam will see all the items
        flux.subscribe(Util.subscriber("mike"));  // same time // mike just see the last item as new message
        flux.subscribe(Util.subscriber("rem"));  // same time // rem just see the last item as new message

        sink.tryEmitNext("new message");
    }
}
