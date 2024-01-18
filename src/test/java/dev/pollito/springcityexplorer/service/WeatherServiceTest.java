package dev.pollito.springcityexplorer.service;

import static dev.pollito.springcityexplorer.MockData.mockWeather;
import static dev.pollito.springcityexplorer.MockData.mockWeatherstackWeather;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.weatherstack.api.WeatherApi;
import dev.pollito.springcityexplorer.client.WeatherClient;
import dev.pollito.springcityexplorer.config.properties.WeatherProperties;
import dev.pollito.springcityexplorer.mapper.WeatherMapper;
import dev.pollito.springcityexplorer.models.Weather;
import dev.pollito.springcityexplorer.service.impl.WeatherServiceImpl;
import java.util.Map;
import net.datafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {
  @InjectMocks private WeatherServiceImpl weatherService;
  @Mock private WeatherProperties weatherProperties;
  @Mock private WeatherClient weatherClient;
  @Spy private WeatherMapper weatherMapper = Mappers.getMapper(WeatherMapper.class);

  Faker faker = new Faker();

  @Test
  @DisplayName("Should return expected weather data for a given city")
  void whenGetWeatherByCityThenOK() {
    when(weatherProperties.getSecrets()).thenReturn(Map.of("key", faker.internet().password()));
    when(weatherClient.currentGet(any(WeatherApi.CurrentGetQueryParams.class)))
        .thenReturn(mockWeatherstackWeather());
    Weather expectedResponse = mockWeather();
    Weather actualResponse = weatherService.getWeatherByCity(faker.address().city());

    assertEquals(expectedResponse, actualResponse);
  }

  @Test
  @DisplayName("Tests weatherMapper ability to handle null current and location")
  void whenWeatherValuesAreNullStillOk() {
    com.weatherstack.models.Weather weatherstackWeather = mockWeatherstackWeather();
    weatherstackWeather.setCurrent(null);
    weatherstackWeather.setLocation(null);

    Weather weather = mockWeather();
    weather.setCurrent(null);
    weather.setLocation(null);

    when(weatherProperties.getSecrets()).thenReturn(Map.of("key", faker.internet().password()));
    when(weatherClient.currentGet(any(WeatherApi.CurrentGetQueryParams.class)))
        .thenReturn(weatherstackWeather);
    Weather actualResponse = weatherService.getWeatherByCity(faker.address().city());

    assertEquals(weather, actualResponse);
  }

  @Test
  @DisplayName("Tests weatherMapper ability to handle null weather")
  void whenWeatherisNullStillOk() {
    when(weatherProperties.getSecrets()).thenReturn(Map.of("key", faker.internet().password()));
    when(weatherClient.currentGet(any(WeatherApi.CurrentGetQueryParams.class))).thenReturn(null);

    assertNull(weatherService.getWeatherByCity(faker.address().city()));
  }
}
