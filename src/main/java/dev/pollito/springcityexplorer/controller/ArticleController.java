package dev.pollito.springcityexplorer.controller;

import dev.pollito.springcityexplorer.api.ArticleApi;
import dev.pollito.springcityexplorer.models.Articles;
import dev.pollito.springcityexplorer.models.CountryEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController implements ArticleApi {

  @Override
  public ResponseEntity<Articles> getArticlesByCountry(
      CountryEnum country, Integer limit, Integer offset) {
    return ArticleApi.super.getArticlesByCountry(country, limit, offset);
  }
}
