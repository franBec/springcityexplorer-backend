package dev.pollito.springcityexplorer.controller.advice;

import static dev.pollito.springcityexplorer.util.ControllerAdviceUtil.getBadRequestError;
import static dev.pollito.springcityexplorer.util.ControllerAdviceUtil.getGenericError;

import dev.pollito.springcityexplorer.exception.WeatherException;
import dev.pollito.springcityexplorer.models.Error;
import feign.codec.DecodeException;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalControllerAdvice {
  public static final String GENERIC_ERROR_LOG_MESSAGE =
      "A generic error is about to be returned. This may be caused by an unhandled exception";

  private final WeatherControllerAdvice weatherControllerAdvice;

  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ResponseEntity<Error> handle(MissingServletRequestParameterException e) {
    return getBadRequestError(e);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<Error> handle(ConstraintViolationException e) {
    return getBadRequestError(e);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<Error> handle(MethodArgumentTypeMismatchException e) {
    return getBadRequestError(e);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Error> handle(MethodArgumentNotValidException e) {
    return getBadRequestError(e);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Error> handle(Exception e) {
    if (isWeatherException(e)) {
      return weatherControllerAdvice.handle((WeatherException) e.getCause());
    }
    log.error(GENERIC_ERROR_LOG_MESSAGE, e);
    return getGenericError(e);
  }

  private boolean isWeatherException(Exception e) {
    return e instanceof DecodeException && e.getCause() instanceof WeatherException;
  }
}
