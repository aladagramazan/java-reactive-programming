package com.rem.reactive_programming_playground.sec03;

import com.rem.reactive_programming_playground.common.Util;
import com.rem.reactive_programming_playground.sec03.helper.NameGenerator;
import com.rem.reactive_programming_playground.subscriber.SubscriberImpl;

public class Lec07FluxVsList {

    public static void main(String[] args) {
      //  var list = NameGenerator.getNames(5);
       // System.out.println(list);

        //var list = NameGenerator.getNames(10);


        var subscriber = new SubscriberImpl();

        NameGenerator.getNamesFlux(10)
                .subscribe(subscriber);

        subscriber.getSubscription().request(3);
        subscriber.getSubscription().cancel();
    }
}
