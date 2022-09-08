package com.micronaut.newsapi;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.View;
import reactor.core.publisher.Mono;

@Controller("/v1")
public class NewsAPIController {

    //@Client(NewsAPIConfiguration.API_URL) @Inject HttpClient httpClient;
    private final NewsAPIClient newsAPIClient;

    public NewsAPIController(NewsAPIClient newsAPIClient) {
        this.newsAPIClient = newsAPIClient;
    }

    @Get(value = "/news/{query}")
    @View("index")
    public HttpResponse <Mono<NewsResults>> fetchNewsArticles(String query) {
        return HttpResponse.ok(newsAPIClient.fetchArticles(query));
    }

}
