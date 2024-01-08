package dev.pollito.springcityexplorer.validator;

import dev.pollito.springcityexplorer.annotation.ValidArticleCountry;
import dev.pollito.springcityexplorer.properties.validation.ArticleCountryProperties;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Objects;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ArticleCountryValidator implements ConstraintValidator<ValidArticleCountry, String> {
  private final ArticleCountryProperties articleCountryProperties;

  @Override
  public void initialize(ValidArticleCountry constraintAnnotation) {
    // You can initialize validator state here if needed
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    boolean isValid = Objects.isNull(value) || articleCountryProperties.getCodes().contains(value);
    if (!isValid) {
      context.disableDefaultConstraintViolation();
      context
          .buildConstraintViolationWithTemplate(articleCountryProperties.getErrorMessage())
          .addConstraintViolation();
    }
    return isValid;
  }
}
