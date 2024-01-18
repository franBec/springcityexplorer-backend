package dev.pollito.springcityexplorer.controller;

import static dev.pollito.springcityexplorer.MockData.mockArticles;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import dev.pollito.springcityexplorer.models.Articles;
import dev.pollito.springcityexplorer.models.CountryEnum;
import dev.pollito.springcityexplorer.service.ArticleService;
import net.datafaker.Faker;
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

  private final Faker faker = new Faker();

  @Test
  void whenGetArticlesByCountryThenOK() {
    ResponseEntity<Articles> expectedResponse = ResponseEntity.ok(mockArticles());
    when(articleService.getArticlesByCountry(any(CountryEnum.class), anyInt(), anyInt()))
        .thenReturn(expectedResponse.getBody());

    ResponseEntity<Articles> actualResponse =
        articleController.getArticlesByCountry(
            CountryEnum.AR,
            faker.number().numberBetween(1, 10),
            faker.number().numberBetween(0, 10000));
    assertTrue(actualResponse.getStatusCode().is2xxSuccessful());
    assertEquals(expectedResponse.getBody(), actualResponse.getBody());
  }
}
