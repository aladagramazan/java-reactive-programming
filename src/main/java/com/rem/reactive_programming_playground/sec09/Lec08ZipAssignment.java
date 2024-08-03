package com.rem.reactive_programming_playground.sec09;

import com.rem.reactive_programming_playground.common.Util;
import com.rem.reactive_programming_playground.sec09.client.ExternalServiceClient;

public class Lec08ZipAssignment {

    public static void main(String[] args) {
        var client = new ExternalServiceClient();

        for (int i = 1; i < 10; i++) {
            client.getProduct(i)
                    .subscribe(Util.subscriber());
        }

        Util.sleepSeconds(2);
    }
}
