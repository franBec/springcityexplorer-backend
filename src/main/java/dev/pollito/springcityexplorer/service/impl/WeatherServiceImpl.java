package dev.pollito.springcityexplorer.service.impl;

import com.weatherstack.api.WeatherApi;
import com.weatherstack.models.WeatherStackCurrentWeather;
import dev.pollito.springcityexplorer.client.WeatherClient;
import dev.pollito.springcityexplorer.config.properties.WeatherProperties;
import dev.pollito.springcityexplorer.models.Weather;
import dev.pollito.springcityexplorer.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

  private final WeatherClient weatherClient;
  private final WeatherProperties weatherProperties;

  @Override
  public Weather getWeatherByCity(String city) {
    WeatherStackCurrentWeather weather =
        weatherClient.currentGet(
            new WeatherApi.CurrentGetQueryParams()
                .accessKey(weatherProperties.getSecrets().get("key"))
                .query(city));
    return null;
  }
}
