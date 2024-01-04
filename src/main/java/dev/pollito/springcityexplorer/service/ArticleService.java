package dev.pollito.springcityexplorer.service;

import dev.pollito.springcityexplorer.models.Articles;

public interface ArticleService {
    Articles getArticlesByCountry(
            String country, Integer limit, Integer offset);
}
