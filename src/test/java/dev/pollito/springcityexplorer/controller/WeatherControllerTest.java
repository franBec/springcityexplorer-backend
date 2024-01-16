package dev.pollito.springcityexplorer.controller;

import static dev.pollito.springcityexplorer.MockData.MOCK_STRING;
import static dev.pollito.springcityexplorer.MockData.mockWeather;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import dev.pollito.springcityexplorer.models.Weather;
import dev.pollito.springcityexplorer.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class WeatherControllerTest {

  @InjectMocks private WeatherController weatherController;
  @Mock private WeatherService weatherService;

  @Test
  void whenGetWeatherByCityThenReturnsWeather() {

    ResponseEntity<Weather> expectedResponse = ResponseEntity.ok(mockWeather());
    when(weatherService.getWeatherByCity(anyString())).thenReturn(expectedResponse.getBody());

    ResponseEntity<Weather> actualResponse = weatherController.getWeatherByCity(MOCK_STRING);
    assertTrue(actualResponse.getStatusCode().is2xxSuccessful());
    assertEquals(expectedResponse.getBody(), actualResponse.getBody());
  }
}
