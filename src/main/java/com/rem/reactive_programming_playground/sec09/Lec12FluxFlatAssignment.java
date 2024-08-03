package com.rem.reactive_programming_playground.sec09;

import com.rem.reactive_programming_playground.common.Util;
import com.rem.reactive_programming_playground.sec09.applications.OrderService;
import com.rem.reactive_programming_playground.sec09.applications.User;
import com.rem.reactive_programming_playground.sec09.applications.UserService;
import com.rem.reactive_programming_playground.sec09.client.ExternalServiceClient;
import reactor.core.publisher.Flux;

public class Lec12FluxFlatAssignment {

    public static void main(String[] args) {
       var client = new ExternalServiceClient();
        Flux.range(1, 10)
                .flatMap(client::getProduct, 3)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(20);
    }
}
