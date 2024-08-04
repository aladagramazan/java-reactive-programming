package com.rem.reactive_programming_playground.sec10;

import com.rem.reactive_programming_playground.common.Util;
import com.rem.reactive_programming_playground.sec10.assignment.buffer.BookOrder;
import com.rem.reactive_programming_playground.sec10.assignment.buffer.RevenueReport;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lec02BufferAssignment {

    public static void main(String[] args) {

        var allowedCategories = Set.of("Science Fiction",
                "Fantasy",
                "Biography/Autobiography",
                "Fable",
                "Suspense",
                "Thriller");

        orderStream()
                .filter(p -> allowedCategories.contains(p.genre()))
                .buffer(Duration.ofSeconds(5))
                .map(Lec02BufferAssignment::generateReport)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);

    }

    private static Flux<BookOrder> orderStream() {
        return Flux.interval(Duration.ofMillis(200))
                .map(i -> BookOrder.create());
    }

    private static RevenueReport generateReport(List<BookOrder> orders) {
        var revenue = orders.stream()
                .collect(Collectors.groupingBy
                        (BookOrder::genre,
                                Collectors.summingInt(BookOrder::price)));

        return new RevenueReport(LocalTime.now(), revenue);
    }
}
