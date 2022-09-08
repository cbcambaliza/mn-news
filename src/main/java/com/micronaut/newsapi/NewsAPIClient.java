package com.micronaut.newsapi;

import io.micronaut.cache.annotation.CacheConfig;
import io.micronaut.cache.annotation.Cacheable;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.uri.UriBuilder;
import reactor.core.publisher.Mono;
import jakarta.inject.Singleton;

import java.net.URI;

import static io.micronaut.http.HttpHeaders.AUTHORIZATION;
import static io.micronaut.http.HttpHeaders.USER_AGENT;


/*
* Low-level client
*/
@Singleton
@CacheConfig("articles")
public class NewsAPIClient {

    private final HttpClient httpClient;
    private URI uri;

    public NewsAPIClient(@Client(NewsAPIConfiguration.API_URL) HttpClient httpClient,
                         NewsAPIConfiguration configuration) {
        this.httpClient = httpClient;
    }

    @Cacheable
    Mono<NewsResults> fetchArticles(String query){
        uri = UriBuilder.of("/v2/everything")
                .queryParam("q", query)
                .build();
        HttpRequest<?> req = HttpRequest.GET(uri)
                .header(USER_AGENT, "Micronaut HTTP Client")
                .header(AUTHORIZATION, NewsAPIConfiguration.API_KEY);
        return Mono.from(httpClient.retrieve(req, NewsResults.class));
    }

}
