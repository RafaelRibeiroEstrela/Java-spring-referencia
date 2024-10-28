package com.example.springsecurity.models;

import org.springframework.http.HttpMethod;

public class Endpoint {

    private HttpMethod method;
    private String url;

    public Endpoint(HttpMethod method, String url) {
        this.method = method;
        this.url = url;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
