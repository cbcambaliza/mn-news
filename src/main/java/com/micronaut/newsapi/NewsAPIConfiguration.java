package com.micronaut.newsapi;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.context.annotation.Requires;

@ConfigurationProperties(NewsAPIConfiguration.PREFIX)
@Requires(property = NewsAPIConfiguration.PREFIX)
public class NewsAPIConfiguration {

    public static final String PREFIX = "newsapi";
    public static final String API_URL = "https://newsapi.org";
    public static final String API_KEY = "ca14d780828445c18e354d1f38bc8420";

    private String apikey;

    public String getApiKey() {
        return apikey;
    }

    public void setApiKey(String apikey) {
        this.apikey = apikey;
    }
}
