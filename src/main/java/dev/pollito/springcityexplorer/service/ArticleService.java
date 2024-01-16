package dev.pollito.springcityexplorer.service;

import dev.pollito.springcityexplorer.models.Articles;
import dev.pollito.springcityexplorer.models.CountryEnum;

public interface ArticleService {
  Articles getArticlesByCountry(CountryEnum country, Integer limit, Integer offset);
}
