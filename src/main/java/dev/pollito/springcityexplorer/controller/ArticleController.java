package dev.pollito.springcityexplorer.controller;

import dev.pollito.springcityexplorer.api.ArticleApi;
import dev.pollito.springcityexplorer.models.Articles;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController implements ArticleApi {
  @Override
  public ResponseEntity<Articles> getArticlesByCountry(
      String country, Integer limit, Integer offset) {
    return null;
  }
}
