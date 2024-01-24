package dev.pollito.springcityexplorer.controller.advice;

import static dev.pollito.springcityexplorer.util.ControllerAdviceUtil.getGenericError;
import static dev.pollito.springcityexplorer.util.ControllerAdviceUtil.getWeatherBadRequestError;

import dev.pollito.springcityexplorer.controller.WeatherController;
import dev.pollito.springcityexplorer.exception.WeatherException;
import dev.pollito.springcityexplorer.models.Error;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = WeatherController.class)
@Slf4j
public class WeatherControllerAdvice {

  public static final int BAD_REQUEST_ERROR_CODE = 615;
  public static final String WEATHER_EXCEPTION_LOG_ERROR_MESSAGE =
      "{} is not caused by Bad Request. Error code: {}. Check API Error Codes in the docs https://weatherstack.com/documentation";

  @ExceptionHandler(WeatherException.class)
  public ResponseEntity<Error> handle(WeatherException e) {
    if (isBadRequest(e)) {
      return getWeatherBadRequestError(e);
    }
    log.error(
        WEATHER_EXCEPTION_LOG_ERROR_MESSAGE,
        e.getClass().getSimpleName(),
        Objects.requireNonNull(e.getWeatherStackError().getError()).getCode());
    return getGenericError(e);
  }

  private boolean isBadRequest(WeatherException e) {
    return Objects.nonNull(e.getWeatherStackError().getError())
        && Objects.nonNull(e.getWeatherStackError().getError().getCode())
        && e.getWeatherStackError().getError().getCode() == BAD_REQUEST_ERROR_CODE;
  }
}
