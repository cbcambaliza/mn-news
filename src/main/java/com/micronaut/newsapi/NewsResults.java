package com.micronaut.newsapi;

import io.micronaut.core.annotation.Introspected;

import java.util.List;

@Introspected
public class NewsResults {

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public List<Article> articles;

}
