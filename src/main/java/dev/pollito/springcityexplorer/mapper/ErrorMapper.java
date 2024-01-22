package dev.pollito.springcityexplorer.mapper;

import dev.pollito.springcityexplorer.exception.WeatherException;
import dev.pollito.springcityexplorer.models.Error;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ErrorMapper {

  String JAVA_TIME_OFFSET_DATE_TIME_NOW = "java(java.time.OffsetDateTime.now())";
  String JAVA_E_GET_CLASS_GET_SIMPLE_NAME = "java(e.getClass().getSimpleName())";

  @Mapping(expression = JAVA_TIME_OFFSET_DATE_TIME_NOW, target = "timestamp")
  @Mapping(expression = JAVA_E_GET_CLASS_GET_SIMPLE_NAME, target = "error")
  @Mapping(source = "e.message", target = "message")
  Error map(Exception e);

  @Mapping(expression = JAVA_E_GET_CLASS_GET_SIMPLE_NAME, target = "error")
  @Mapping(expression = JAVA_TIME_OFFSET_DATE_TIME_NOW, target = "timestamp")
  @Mapping(source = "e.weatherStackError.error.type", target = "message")
  Error map(WeatherException e);
}
