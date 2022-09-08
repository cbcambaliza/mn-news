package com.micronaut;

import com.micronaut.newsapi.Article;
import com.micronaut.newsapi.NewsResults;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.uri.UriBuilder;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
public class NewsApiTest {

    @Inject
    @Client("/")
    HttpClient httpClient;

    private static final String query = "moomin";

    @Test
    public void verifyNewsArticlesCanBeFetched(){

        //when:
        HttpRequest<Object> request = HttpRequest.GET( UriBuilder.of("/v1/news")
                .path(query)
                .build());

        HttpResponse<NewsResults> rsp = httpClient.toBlocking().exchange(request,
                NewsResults.class);

        //then: 'the endpoint can be accessed'
        assertEquals(HttpStatus.OK, rsp.getStatus());
        assertNotNull(rsp.body());

        //when:
        NewsResults newsResults = rsp.body();

        //then:
        assertNotNull(newsResults);

        assertTrue(newsResults.getArticles().stream()
                .map(Article::getDescription)
                .anyMatch((desc -> Pattern.compile(Pattern.quote(query), Pattern.CASE_INSENSITIVE)
                                .matcher(desc)
                                .find())));

        assertTrue(newsResults.getArticles().stream()
                .filter(e -> !e.getDescription().contains(query))
                .map(Article::getDescription)
                .anyMatch(desc -> desc.toLowerCase().contains(query.toLowerCase())));
    }
}
