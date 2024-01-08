package dev.pollito.springcityexplorer.service;

import dev.pollito.springcityexplorer.models.Weather;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public interface WeatherService {
  Weather getWeatherByCity(@NotBlank String city);
}
