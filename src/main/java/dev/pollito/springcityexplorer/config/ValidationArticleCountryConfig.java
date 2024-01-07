package dev.pollito.springcityexplorer.config;

import java.util.List;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "validation.article.country")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ValidationArticleCountryConfig {
  List<String> codes;
  String errorMessage;
}
