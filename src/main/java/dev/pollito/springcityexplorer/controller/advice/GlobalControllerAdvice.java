package dev.pollito.springcityexplorer.controller.advice;

import static dev.pollito.springcityexplorer.util.ControllerAdviceUtil.getBadRequestError;
import static dev.pollito.springcityexplorer.util.ControllerAdviceUtil.getGenericError;

import dev.pollito.springcityexplorer.exception.WeatherException;
import dev.pollito.springcityexplorer.models.Error;
import feign.codec.DecodeException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Objects;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {

  private final WeatherControllerAdvice weatherControllerAdvice;

  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ResponseEntity<Error> handle(MissingServletRequestParameterException e){
    return getBadRequestError(e.getClass().getSimpleName(), e.getBody().getDetail());
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<Error> handle(ConstraintViolationException e){
    return getBadRequestError(e.getClass().getSimpleName(), constraintViolationExceptionMessageFormatter(e));
  }

  private static String constraintViolationExceptionMessageFormatter(ConstraintViolationException e){
    String formattedMessage = "";

    if (!e.getConstraintViolations().isEmpty()) {
      ConstraintViolation<?> violation = e.getConstraintViolations().iterator().next();
      String propertyPath = violation.getPropertyPath().toString();
      String fieldName = propertyPath.substring(propertyPath.lastIndexOf('.') + 1);
      String message = violation.getMessage();
      formattedMessage = fieldName + ": " + message;
    }

    return formattedMessage;
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<Error> handle(MethodArgumentTypeMismatchException e){
    return getBadRequestError(e.getClass().getSimpleName(), e.getCause().getCause().getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Error> handle(MethodArgumentNotValidException e){
    return getBadRequestError(e.getClass().getSimpleName(), Objects.requireNonNull(e.getDetailMessageArguments())[1].toString().replace("[", "").replace("]", ""));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Error> handle(Exception e) {
    if (isWeatherException(e)) {
      return weatherControllerAdvice.handle((WeatherException) e.getCause());
    }
    return getGenericError(e);
  }

  private boolean isWeatherException(Exception e) {
    return e instanceof DecodeException && e.getCause() instanceof WeatherException;
  }
}
