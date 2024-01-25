package dev.pollito.springcityexplorer.config;

import static dev.pollito.springcityexplorer.util.Constants.WEATHER_CACHE;

import com.github.benmanes.caffeine.cache.Caffeine;
import dev.pollito.springcityexplorer.config.properties.WeatherProperties;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
@RequiredArgsConstructor
public class CacheConfig {
  private final WeatherProperties weatherProperties;

  @Bean
  public CacheManager cacheManager() {
    CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager(WEATHER_CACHE);
    caffeineCacheManager.setCaffeine(
        Caffeine.newBuilder()
            .expireAfterWrite(weatherProperties.getExpiresAfter(), TimeUnit.SECONDS));

    return caffeineCacheManager;
  }
}
