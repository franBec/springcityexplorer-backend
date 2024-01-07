package dev.pollito.springcityexplorer.validator;

import dev.pollito.springcityexplorer.annotation.ValidArticleCountry;
import dev.pollito.springcityexplorer.config.ValidationArticleCountryConfig;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Objects;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ArticleCountryValidator implements ConstraintValidator<ValidArticleCountry, String> {
  private final ValidationArticleCountryConfig validationArticleCountryConfig;

  @Override
  public void initialize(ValidArticleCountry constraintAnnotation) {
    // You can initialize validator state here if needed
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    boolean isValid =
        Objects.isNull(value) || validationArticleCountryConfig.getCodes().contains(value);
    if (!isValid) {
      context.disableDefaultConstraintViolation();
      context
          .buildConstraintViolationWithTemplate(validationArticleCountryConfig.getErrorMessage())
          .addConstraintViolation();
    }
    return isValid;
  }
}
