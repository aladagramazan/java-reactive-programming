package com.rem.reactive_programming_playground.sec09;

import com.rem.reactive_programming_playground.common.Util;
import com.rem.reactive_programming_playground.sec09.applications.OrderService;
import com.rem.reactive_programming_playground.sec09.applications.UserService;

public class Lec10MonoFlatMapMany {

    public static void main(String[] args) {
        UserService.getUserId("rem")
                .flatMapMany(OrderService::getOrdersForUser)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(5);
    }
}
