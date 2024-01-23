package dev.pollito.springcityexplorer.controller.advice;

import static dev.pollito.springcityexplorer.util.ControllerAdviceUtil.getGenericError;
import static dev.pollito.springcityexplorer.util.ControllerAdviceUtil.getWeatherBadRequestError;

import dev.pollito.springcityexplorer.controller.WeatherController;
import dev.pollito.springcityexplorer.exception.WeatherException;
import dev.pollito.springcityexplorer.models.Error;
import java.util.Objects;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = WeatherController.class)
public class WeatherControllerAdvice {

  @ExceptionHandler(WeatherException.class)
  public ResponseEntity<Error> handle(WeatherException e) {
    if (isBadRequest(e)) {
      return getWeatherBadRequestError(e);
    }
    return getGenericError(e);
  }

  private boolean isBadRequest(WeatherException e) {
    return Objects.nonNull(e.getWeatherStackError().getError())
        && Objects.nonNull(e.getWeatherStackError().getError().getCode())
        && e.getWeatherStackError().getError().getCode() == 615;
  }
}
