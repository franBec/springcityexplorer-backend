package dev.pollito.springcityexplorer.controller;

import dev.pollito.springcityexplorer.api.WeatherApi;
import dev.pollito.springcityexplorer.models.Weather;
import dev.pollito.springcityexplorer.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WeatherController implements WeatherApi {

  private final WeatherService weatherService;

  @Override
  public ResponseEntity<Weather> getWeatherByCity(String city) {
    return ResponseEntity.ok(weatherService.getWeatherByCity(city));
  }
}
