package dev.pollito.springcityexplorer.controller;

import dev.pollito.springcityexplorer.api.ArticleApi;
import dev.pollito.springcityexplorer.models.Articles;
import dev.pollito.springcityexplorer.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ArticleController implements ArticleApi {

  private final ArticleService articleService;

  @Override
  public ResponseEntity<Articles> getArticlesByCountry(
      String country, Integer limit, Integer offset) {
    return ResponseEntity.ok(articleService.getArticlesByCountry(country, limit, offset));
  }
}
