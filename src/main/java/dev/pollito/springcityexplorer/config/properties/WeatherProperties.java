package dev.pollito.springcityexplorer.config.properties;

import java.util.Map;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "client.weather")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WeatherProperties {
  String baseUrl;
  Map<String, String> secrets;
}
