package com.rem.reactive_programming_playground.sec09;

import com.rem.reactive_programming_playground.common.Util;
import com.rem.reactive_programming_playground.sec09.applications.Order;
import com.rem.reactive_programming_playground.sec09.applications.OrderService;
import com.rem.reactive_programming_playground.sec09.applications.User;
import com.rem.reactive_programming_playground.sec09.applications.UserService;
import reactor.core.publisher.Flux;

public class Lec11FluxFlatMap {

    public static void main(String[] args) {
        UserService.getAllUsers()
                .map(User::id)
                .flatMap(OrderService::getOrdersForUser)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(5);
    }
}
