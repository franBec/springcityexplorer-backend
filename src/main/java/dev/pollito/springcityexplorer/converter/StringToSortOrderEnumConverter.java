package dev.pollito.springcityexplorer.converter;

import dev.pollito.springcityexplorer.models.SortOrderEnum;
import org.springframework.core.convert.converter.Converter;

public class StringToSortOrderEnumConverter implements Converter<String, SortOrderEnum> {
  @Override
  public SortOrderEnum convert(String source) {
    return SortOrderEnum.fromValue(source);
  }
}
