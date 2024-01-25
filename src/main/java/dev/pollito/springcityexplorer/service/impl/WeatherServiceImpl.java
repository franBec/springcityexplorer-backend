package dev.pollito.springcityexplorer.service.impl;

import static dev.pollito.springcityexplorer.util.Constants.WEATHER_CACHE;

import com.weatherstack.api.WeatherApi;
import dev.pollito.springcityexplorer.config.properties.WeatherProperties;
import dev.pollito.springcityexplorer.mapper.WeatherMapper;
import dev.pollito.springcityexplorer.models.Weather;
import dev.pollito.springcityexplorer.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

  private final WeatherApi weatherApi;
  private final WeatherProperties weatherProperties;
  private final WeatherMapper weatherMapper;

  @Override
  @Cacheable(value = WEATHER_CACHE)
  public Weather getWeatherByCity(String city) {
    return weatherMapper.map(
        weatherApi.currentGet(
            new WeatherApi.CurrentGetQueryParams()
                .accessKey(weatherProperties.getSecrets().get("key"))
                .query(city)));
  }
}
