package dev.pollito.springcityexplorer.service.impl;

import dev.pollito.springcityexplorer.models.Articles;
import dev.pollito.springcityexplorer.service.ArticleService;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Override
    public Articles getArticlesByCountry(String country, Integer limit, Integer offset) {
        return null;
    }
}
