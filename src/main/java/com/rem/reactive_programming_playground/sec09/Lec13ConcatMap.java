package com.rem.reactive_programming_playground.sec09;

import com.rem.reactive_programming_playground.common.Util;
import com.rem.reactive_programming_playground.sec09.client.ExternalServiceClient;
import reactor.core.publisher.Flux;

public class Lec13ConcatMap {

    public static void main(String[] args) {
        var client = new ExternalServiceClient();
        Flux.range(1, 10)
                .concatMap(client::getProduct)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(20);
    }
}
