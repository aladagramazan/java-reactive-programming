package com.rem.reactive_programming_playground.sec07;

import com.rem.reactive_programming_playground.common.Util;
import com.rem.reactive_programming_playground.sec07.client.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec05EventLoopIssueFix {
    public static void main(String[] args) {
        var client = new ExternalServiceClient();
        for (int i = 1; i < 5; i++) {
            client.getProductName(i)
                    .map(Lec05EventLoopIssueFix::process)
                    .subscribe(Util.subscriber());
        }

        Util.sleepSeconds(20);
    }

    private static String process(String input) {
        Util.sleepSeconds(1);
        return input + "_processed";
    }
}
