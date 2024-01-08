package dev.pollito.springcityexplorer.validator;

import dev.pollito.springcityexplorer.annotation.ValidCommentSortOrder;
import dev.pollito.springcityexplorer.properties.validation.CommentSortOrderProperties;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Objects;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CommentSortOrderValidator
    implements ConstraintValidator<ValidCommentSortOrder, String> {
  private final CommentSortOrderProperties commentSortOrderProperties;

  @Override
  public void initialize(ValidCommentSortOrder constraintAnnotation) {
    // You can initialize validator state here if needed
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    boolean isValid =
        Objects.isNull(value) || commentSortOrderProperties.getValues().contains(value);
    if (!isValid) {
      context.disableDefaultConstraintViolation();
      context
          .buildConstraintViolationWithTemplate(commentSortOrderProperties.getErrorMessage())
          .addConstraintViolation();
    }
    return isValid;
  }
}
