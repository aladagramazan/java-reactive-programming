package com.rem.reactive_programming_playground.sec10.assignment.groupby;

import com.rem.reactive_programming_playground.common.Util;

public record PurchaseOrder(String item,
                            String category,
                            Integer price) {

    public static PurchaseOrder create() {
        var item = Util.faker().commerce().productName();
        var category = Util.faker().commerce().department();
        var price = Util.faker().random().nextInt(50, 100);
        return new PurchaseOrder(item, category, price);
    }
}
