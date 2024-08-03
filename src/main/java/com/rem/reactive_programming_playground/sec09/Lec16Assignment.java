package com.rem.reactive_programming_playground.sec09;

import com.rem.reactive_programming_playground.common.Util;
import com.rem.reactive_programming_playground.sec09.applications.*;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec16Assignment {

    record UserInformation(Integer userId, String userName, Integer balance, List<Order> orders) {}

    public static void main(String[] args) {
        UserService.getAllUsers()
                .flatMap(Lec16Assignment::getUserInformation)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(2);
    }

    private static Mono<UserInformation> getUserInformation(User user) {
        return Mono.zip(PaymentService.getUserBalance(user.id()),
                        OrderService.getOrdersForUser(user.id()).collectList())
                .map(tuple -> new UserInformation(user.id(), user.username(), tuple.getT1(), tuple.getT2()));
    }
}
