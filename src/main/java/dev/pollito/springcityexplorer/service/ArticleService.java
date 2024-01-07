package dev.pollito.springcityexplorer.service;

import dev.pollito.springcityexplorer.annotation.ValidArticleCountry;
import dev.pollito.springcityexplorer.models.Articles;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;

@Validated
public interface ArticleService {
  Articles getArticlesByCountry(
      @ValidArticleCountry String country,
      @Min(1) @Max(10) Integer limit,
      @Min(0) @Max(10000) Integer offset);
}
