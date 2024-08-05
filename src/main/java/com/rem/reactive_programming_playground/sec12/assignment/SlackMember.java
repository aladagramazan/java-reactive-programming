package com.rem.reactive_programming_playground.sec12.assignment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SlackMember {

    private static final Logger log = LoggerFactory.getLogger(SlackMember.class);

    private String name;
    private Consumer<String> messageConsumer;

    public void says(String message) {
        messageConsumer.accept(message);
    }

    void receiveMessage(String message) {
        log.info("Received message: {}", message);
    }

}
