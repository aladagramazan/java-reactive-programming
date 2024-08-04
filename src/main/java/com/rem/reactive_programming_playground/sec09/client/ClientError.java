package com.rem.reactive_programming_playground.sec09.client;

public class ClientError extends RuntimeException {
    public ClientError() {
        super("bad request");
    }
}
