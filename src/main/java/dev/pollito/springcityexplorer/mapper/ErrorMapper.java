package dev.pollito.springcityexplorer.mapper;

import dev.pollito.springcityexplorer.models.Error;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ErrorMapper {

  @Mapping(expression = "java(java.time.OffsetDateTime.now())", target = "timestamp")
  @Mapping(expression = "java(e.getClass().getSimpleName())", target = "error")
  @Mapping(source = "e.message", target = "message")
  Error map(Exception e);
}
