package com.rem.reactive_programming_playground.sec10.assignment.buffer;

import java.time.LocalTime;
import java.util.Map;

public record RevenueReport(LocalTime time,
                            Map<String, Integer> revenue) {
}
