package com.rem.reactive_programming_playground.sec03;

import com.rem.reactive_programming_playground.common.Util;
import com.rem.reactive_programming_playground.sec02.client.ExternalServiceClient;
import com.rem.reactive_programming_playground.sec03.assignment.StockPriceObserver;

public class Lec12Assignment {

    public static void main(String[] args) {
        var client = new ExternalServiceClient();
        var subscriber = new StockPriceObserver();

        client.getPriceChanges()
                .subscribe(subscriber);

        Util.sleepSeconds(20);
    }
}
