package dev.pollito.springcityexplorer.config.api;

import com.weatherstack.api.WeatherApi;
import dev.pollito.springcityexplorer.config.properties.WeatherProperties;
import dev.pollito.springcityexplorer.decoder.WeatherResponseDecoder;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScans(
    value = {
      @ComponentScan(
          basePackages = {
            "com.weatherstack.api",
          })
    })
@RequiredArgsConstructor
public class WeatherApiConfig {

  private final WeatherProperties weatherProperties;

  @Bean
  public WeatherApi weatherApi() {
    return Feign.builder()
        .client(new OkHttpClient())
        .encoder(new GsonEncoder())
        .decoder(new WeatherResponseDecoder())
        .logger(new Slf4jLogger(WeatherApi.class))
        .logLevel(Logger.Level.FULL)
        .target(WeatherApi.class, weatherProperties.getBaseUrl());
  }
}
