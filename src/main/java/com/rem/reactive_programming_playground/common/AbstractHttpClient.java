package com.rem.reactive_programming_playground.common;

import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.LoopResources;

public abstract class AbstractHttpClient {

    private static final String BASE_URL = "http://localhost:7070";
    protected final HttpClient client;

    public AbstractHttpClient() {
        // i just want to have only one thread for the http client
        var loopResources = LoopResources.create("van", 1, true);
        this.client = HttpClient.create().runOn(loopResources)
                .baseUrl(BASE_URL);
    }
}
