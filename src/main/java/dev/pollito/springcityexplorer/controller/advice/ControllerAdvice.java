package dev.pollito.springcityexplorer.controller.advice;

import dev.pollito.springcityexplorer.exception.WeatherException;
import dev.pollito.springcityexplorer.mapper.ErrorMapper;
import feign.codec.DecodeException;
import java.time.OffsetDateTime;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvice {
  private final ErrorMapper errorMapper;

  @ExceptionHandler(WeatherException.class)
  public ResponseEntity<Object> handle(WeatherException e) {
    if (isWeatherBadRequest(e)) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(createBodyWeatherBadRequest(e));
    }
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMapper.map(e));
  }

  private dev.pollito.springcityexplorer.models.Error createBodyWeatherBadRequest(
      WeatherException e) {
    return new dev.pollito.springcityexplorer.models.Error()
        .timestamp(OffsetDateTime.now())
        .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
        .message("City not found");
  }

  private boolean isWeatherBadRequest(WeatherException e) {
    return Objects.nonNull(e.getWeatherStackError().getError())
        && Objects.nonNull(e.getWeatherStackError().getError().getCode())
        && 615 == e.getWeatherStackError().getError().getCode();
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handle(Exception e) {
    if (e instanceof DecodeException && e.getCause() instanceof WeatherException) {
      return handle((WeatherException) e.getCause());
    }
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMapper.map(e));
  }
}
