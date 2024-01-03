package dev.pollito.springcityexplorer.controller;

import dev.pollito.springcityexplorer.api.WeatherApi;
import dev.pollito.springcityexplorer.models.Weather;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController implements WeatherApi {
  @Override
  public ResponseEntity<Weather> getWeatherByCity(String city) {
    return null;
  }
}
