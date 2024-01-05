package dev.pollito.springcityexplorer.controller;

import static dev.pollito.springcityexplorer.MockData.MOCK_INTEGER;
import static dev.pollito.springcityexplorer.MockData.MOCK_STRING;
import static dev.pollito.springcityexplorer.MockData.mockArticles;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import dev.pollito.springcityexplorer.models.Articles;
import dev.pollito.springcityexplorer.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class ArticleControllerTest {
  @InjectMocks private ArticleController articleController;
  @Mock private ArticleService articleService;

  @Test
  void whenGetArticlesByCountryThenReturnsArticles() {
    ResponseEntity<Articles> expectedResponse = ResponseEntity.ok(mockArticles());
    when(articleService.getArticlesByCountry(anyString(), anyInt(), anyInt()))
        .thenReturn(expectedResponse.getBody());

    ResponseEntity<Articles> actualResponse =
        articleController.getArticlesByCountry(MOCK_STRING, MOCK_INTEGER, MOCK_INTEGER);

    assertEquals(expectedResponse.getBody(), actualResponse.getBody());
  }
}
