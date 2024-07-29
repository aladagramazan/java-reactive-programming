package com.rem.reactive_programming_playground.sec05;

import com.rem.reactive_programming_playground.common.Util;
import com.rem.reactive_programming_playground.sec05.assignment.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec11Assignment {

    private static final Logger log = LoggerFactory.getLogger(Lec11Assignment.class);

    public static void main(String[] args) {
        var client = new ExternalServiceClient();

        for (int i = 1; i < 5; i++) {
            client.getProductName(i)
                    .subscribe(Util.subscriber());
        }

        Util.sleepSeconds(3);
    }
}
