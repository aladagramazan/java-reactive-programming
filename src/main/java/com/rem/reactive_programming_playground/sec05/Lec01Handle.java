package com.rem.reactive_programming_playground.sec05;

import com.rem.reactive_programming_playground.common.Util;
import reactor.core.publisher.Flux;

public class Lec01Handle {

    public static void main(String[] args) {
       Flux.range(1, 20)
               .handle((item, sink) -> {
                   switch (item) {
                       case 1 -> sink.next(-3);
                       case 4 -> {}
                       case 7 -> sink.error(new RuntimeException("Error occurred"));
                       default -> sink.next(item);
                   }
               })
               .cast(Integer.class)
               .subscribe(Util.subscriber());
    }
}
