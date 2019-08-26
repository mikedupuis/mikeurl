package com.mikedupuis.weeurl.dto;

public class WeeurlCreateRequest {
    String url;
    String requestedShortUrl;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRequestedShortUrl() {
        return requestedShortUrl;
    }

    public void setRequestedShortUrl(String requestedShortUrl) {
        this.requestedShortUrl = requestedShortUrl;
    }
}
