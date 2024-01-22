package dev.pollito.springcityexplorer.controller.advice;

import dev.pollito.springcityexplorer.mapper.ErrorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvice {
  private final ErrorMapper errorMapper;

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handle(Exception e) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMapper.map(e));
  }
}
