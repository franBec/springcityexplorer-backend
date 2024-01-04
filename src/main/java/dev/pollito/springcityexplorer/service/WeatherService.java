package dev.pollito.springcityexplorer.service;

import dev.pollito.springcityexplorer.models.Weather;

public interface WeatherService {
    Weather getWeatherByCity(String city);
}
