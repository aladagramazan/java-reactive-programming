package com.rem.reactive_programming_playground.sec09.client;

public class ServerError extends RuntimeException {

    public ServerError() {
        super("server error");
    }
}
