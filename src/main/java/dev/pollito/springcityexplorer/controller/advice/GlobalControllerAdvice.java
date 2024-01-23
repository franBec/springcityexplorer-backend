package dev.pollito.springcityexplorer.controller.advice;

import static dev.pollito.springcityexplorer.util.ControllerAdviceUtil.getGenericError;

import dev.pollito.springcityexplorer.exception.WeatherException;
import dev.pollito.springcityexplorer.models.Error;
import feign.codec.DecodeException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {

  private final WeatherControllerAdvice weatherControllerAdvice;

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
