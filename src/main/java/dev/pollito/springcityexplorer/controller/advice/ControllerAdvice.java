package dev.pollito.springcityexplorer.controller.advice;

import dev.pollito.springcityexplorer.exception.WeatherException;
import dev.pollito.springcityexplorer.mapper.ErrorMapper;
import feign.codec.DecodeException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvice {
  private final ErrorMapper errorMapper;

  @ExceptionHandler(WeatherException.class)
  public ResponseEntity<Object> handle(WeatherException e) {
    if(isBadRequest(e)){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMapper.map(e));
    }
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMapper.map(e));
  }

  private boolean isBadRequest(WeatherException e) {
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
