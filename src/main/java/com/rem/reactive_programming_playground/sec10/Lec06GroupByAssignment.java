package com.rem.reactive_programming_playground.sec10;

import com.rem.reactive_programming_playground.common.Util;
import com.rem.reactive_programming_playground.sec10.assignment.groupby.OrderProcessingService;
import com.rem.reactive_programming_playground.sec10.assignment.groupby.PurchaseOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Lec06GroupByAssignment {

    private static final Logger log = LoggerFactory.getLogger(Lec06GroupByAssignment.class);

    public static void main(String[] args) {
        orderStream()
                .filter(OrderProcessingService.canProcess())
                .groupBy(PurchaseOrder::category)
                .flatMap(gf -> gf.transform(OrderProcessingService.getProcessor(gf.key())))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }

    private static Mono<Void> processEvents(GroupedFlux<String, PurchaseOrder> groupedFlux) {
        log.info("received flux for {}", groupedFlux.key());
        return groupedFlux
                .doOnNext(e -> log.info("key: {}, value: {}", groupedFlux.key(), e))
                .doOnComplete(() -> log.info("key: {} completed", groupedFlux.key()))
                .then();
    }

    private static Flux<PurchaseOrder> orderStream() {
        return Flux.interval(Duration.ofMillis(200))
                .map(i -> PurchaseOrder.create());
    }
}
