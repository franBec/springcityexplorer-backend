package dev.pollito.springcityexplorer.exception;

import com.weatherstack.models.WeatherStackError;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class WeatherException extends RuntimeException {
  private final WeatherStackError weatherStackError;
}
