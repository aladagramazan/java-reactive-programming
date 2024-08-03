package com.rem.reactive_programming_playground.sec09;

import com.rem.reactive_programming_playground.common.Util;
import com.rem.reactive_programming_playground.sec09.applications.PaymentService;
import com.rem.reactive_programming_playground.sec09.applications.UserService;
import reactor.core.publisher.Mono;

public class Lec09MonoFlatMap {

    public static void main(String[] args) {
        Mono<Integer> mono =UserService.getUserId("rem")
                .flatMap(PaymentService::getUserBalance);

        mono.subscribe(Util.subscriber());

    }
}
