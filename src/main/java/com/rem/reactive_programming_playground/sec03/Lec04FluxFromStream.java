package com.rem.reactive_programming_playground.sec03;

import com.rem.reactive_programming_playground.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Stream;

public class Lec04FluxFromStream {

    public static void main(String[] args) {
        var list = List.of(1, 2, 3, 4, 5);
         var stream = list.stream();
         var flux = Flux.fromStream(list::stream); // list::stream yaparsam sub2 de calisir

      //   stream.forEach(System.out::println);  // you can not use the stream again, you can only stream once
       // stream.forEach(System.out::println);  // you can not use the stream again, you can only stream once

         flux.subscribe(Util.subscriber("sub1"));
         flux.subscribe(Util.subscriber("sub2"));  // you can not use the stream again, you can only stream once
    }
}
