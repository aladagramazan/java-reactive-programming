package com.rem.reactive_programming_playground.sec03;

import com.rem.reactive_programming_playground.common.Util;
import com.rem.reactive_programming_playground.sec02.client.ExternalServiceClient;

public class Lec08NonBlockingStreamingMessages {

    public static void main(String[] args) {

         var client = new ExternalServiceClient();
         client.getProductNameStream()
                .subscribe(Util.subscriber("sub1"));

         client.getProductNameStream()
                .subscribe(Util.subscriber("sub2"));

         Util.sleepSeconds(10);
    }
}
