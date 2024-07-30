package com.rem.reactive_programming_playground.sec06;

import com.rem.reactive_programming_playground.common.Util;
import com.rem.reactive_programming_playground.sec06.assignment.ExternalServiceClient;
import com.rem.reactive_programming_playground.sec06.assignment.InventoryService;
import com.rem.reactive_programming_playground.sec06.assignment.RevenueService;

public class Lec06Assignment {

    public static void main(String[] args) {

        var client = new ExternalServiceClient();
        var inventoryService = new InventoryService();
        var revenueService = new RevenueService();

        client.orderStream().subscribe(inventoryService::consume);
        client.orderStream().subscribe(revenueService::consume);

        inventoryService.stream().subscribe(Util.subscriber("Inventory"));
        revenueService.stream().subscribe(Util.subscriber("Revenue"));

        Util.sleepSeconds(30);
    }
}
