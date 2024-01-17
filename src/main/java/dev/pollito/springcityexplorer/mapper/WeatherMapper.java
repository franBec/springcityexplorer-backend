package dev.pollito.springcityexplorer.mapper;

import dev.pollito.springcityexplorer.models.Weather;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WeatherMapper {
  Weather map(com.weatherstack.models.Weather weather);
}
