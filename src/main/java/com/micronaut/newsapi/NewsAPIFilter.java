package com.micronaut.newsapi;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpHeaders;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.ClientFilterChain;
import io.micronaut.http.filter.HttpClientFilter;
import org.reactivestreams.Publisher;

import static io.micronaut.http.HttpHeaders.AUTHORIZATION;

@Filter("/**")
@Requires(property = NewsAPIConfiguration.PREFIX + ".apikey")
public class NewsAPIFilter implements HttpClientFilter {

    private final NewsAPIConfiguration configuration;

    public NewsAPIFilter(NewsAPIConfiguration configuration){
        this.configuration = configuration;
    }

    @Override
    public Publisher<? extends HttpResponse<?>> doFilter(MutableHttpRequest<?> request, ClientFilterChain chain) {
  //      MutableHttpHeaders headers = request.getHeaders();
  //      headers.add(AUTHORIZATION, configuration.getApiKey());

        return chain.proceed(request);
    }
}
