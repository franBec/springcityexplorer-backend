package dev.pollito.springcityexplorer;

import dev.pollito.springcityexplorer.models.Articles;
import dev.pollito.springcityexplorer.models.ArticlesData;
import dev.pollito.springcityexplorer.models.ArticlesPagination;
import dev.pollito.springcityexplorer.models.Weather;
import dev.pollito.springcityexplorer.models.WeatherCurrent;
import dev.pollito.springcityexplorer.models.WeatherLocation;
import dev.pollito.springcityexplorer.models.WeatherRequest;

import java.util.List;

public class MockData {
  private MockData() {}

  public static final String MOCK_STRING = "string";
  public static final String MOCK_TIMEZONE_ID = "America/New_York";
  public static final String MOCK_LOCALTIME = "2019-09-07 08:14";
  public static final Integer MOCK_LOCALTIME_EPOCH = 1567844040;
  public static final String MOCK_UTC_OFFSET = "-4";
  public static final Integer MOCK_INTEGER = 0;
  public static final String MOCK_ISO8601_DATE = "2024-01-04T15:30:00Z";

  public static Weather mockWeather() {
    return new Weather()
        .request(
            new WeatherRequest()
                .type(WeatherRequest.TypeEnum.CITY)
                .query(MOCK_STRING)
                .language(MOCK_STRING)
                .unit(WeatherRequest.UnitEnum.M))
        .location(
            new WeatherLocation()
                .name(MOCK_STRING)
                .country(MOCK_STRING)
                .region(MOCK_STRING)
                .lat(MOCK_STRING)
                .lon(MOCK_STRING)
                .timezoneId(MOCK_TIMEZONE_ID)
                .localtime(MOCK_LOCALTIME)
                .localtimeEpoch(MOCK_LOCALTIME_EPOCH)
                .utcOffset(MOCK_UTC_OFFSET))
        .current(
            new WeatherCurrent()
                .observationTime(MOCK_STRING)
                .temperature(MOCK_INTEGER)
                .weatherCode(MOCK_INTEGER)
                .weatherIcons(List.of(MOCK_STRING))
                .weatherDescriptions(List.of(MOCK_STRING))
                .windSpeed(MOCK_INTEGER)
                .windDegree(MOCK_INTEGER)
                .windDir(MOCK_STRING)
                .pressure(MOCK_INTEGER)
                .humidity(MOCK_INTEGER)
                .cloudcover(MOCK_INTEGER)
                .feelslike(MOCK_INTEGER)
                .uvIndex(MOCK_INTEGER)
                .visibility(MOCK_INTEGER));
  }

  public static Articles mockArticles() {
    return new Articles()
        .pagination(
            new ArticlesPagination()
                .limit(MOCK_INTEGER)
                .offset(MOCK_INTEGER)
                .count(MOCK_INTEGER)
                .total(MOCK_INTEGER))
        .data(
            List.of(
                new ArticlesData()
                    .author(MOCK_STRING)
                    .title(MOCK_STRING)
                    .description(MOCK_STRING)
                    .url(MOCK_STRING)
                    .image(MOCK_STRING)
                    .category(ArticlesData.CategoryEnum.GENERAL)
                    .language(ArticlesData.LanguageEnum.AR)
                    .country(ArticlesData.CountryEnum.AR)
                    .publishedAt(MOCK_ISO8601_DATE)
            )
        );
  }
}
