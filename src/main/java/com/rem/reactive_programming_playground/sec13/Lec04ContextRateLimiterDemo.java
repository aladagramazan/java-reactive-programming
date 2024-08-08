package com.rem.reactive_programming_playground.sec13;

import com.rem.reactive_programming_playground.common.Util;
import com.rem.reactive_programming_playground.sec13.client.ExternalServiceClient;
import reactor.util.context.Context;

public class Lec04ContextRateLimiterDemo {

    public static void main(String[] args) {
        var client = new ExternalServiceClient();
        for (int i = 0; i < 20; i++) {
            client.getBook()
                    .contextWrite(Context.of("user", "mike"))
                    .subscribe(Util.subscriber());
            Util.sleepSeconds(1);
        }

        Util.sleepSeconds(5);
    }
}
