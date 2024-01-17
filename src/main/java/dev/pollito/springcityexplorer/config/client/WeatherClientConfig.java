package dev.pollito.springcityexplorer.config.client;

import dev.pollito.springcityexplorer.client.WeatherClient;
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
            "dev.pollito.springcityexplorer.client",
          })
    })
@RequiredArgsConstructor
public class WeatherClientConfig {

  private final WeatherProperties weatherProperties;

  @Bean
  public WeatherClient weatherClient() {
    return Feign.builder()
        .client(new OkHttpClient())
        .encoder(new GsonEncoder())
        .decoder(new WeatherResponseDecoder())
        .logger(new Slf4jLogger(WeatherClient.class))
        .logLevel(Logger.Level.FULL)
        .target(WeatherClient.class, weatherProperties.getBaseUrl());
  }
}
