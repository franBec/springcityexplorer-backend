package dev.pollito.springcityexplorer.converter;

import dev.pollito.springcityexplorer.models.CountryEnum;
import org.springframework.core.convert.converter.Converter;

public class StringToCountryEnumConverter implements Converter<String, CountryEnum> {
  @Override
  public CountryEnum convert(String source) {
    return CountryEnum.fromValue(source);
  }
}
