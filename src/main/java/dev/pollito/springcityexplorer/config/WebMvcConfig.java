package dev.pollito.springcityexplorer.config;

import dev.pollito.springcityexplorer.converter.StringToCountryEnumConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
  @Override
  public void addFormatters(FormatterRegistry registry) {
    registry.addConverter(new StringToCountryEnumConverter());
  }
}
