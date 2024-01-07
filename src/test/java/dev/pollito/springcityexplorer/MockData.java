package dev.pollito.springcityexplorer;

import dev.pollito.springcityexplorer.models.Articles;
import dev.pollito.springcityexplorer.models.ArticlesData;
import dev.pollito.springcityexplorer.models.ArticlesPagination;
import dev.pollito.springcityexplorer.models.CommentPostRequest;
import dev.pollito.springcityexplorer.models.CommentPostResponse;
import dev.pollito.springcityexplorer.models.Comments;
import dev.pollito.springcityexplorer.models.CommentsContent;
import dev.pollito.springcityexplorer.models.SpringDataPageable;
import dev.pollito.springcityexplorer.models.SpringDataSortInfo;
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
                .temperature(0)
                .weatherCode(0)
                .weatherIcons(List.of(MOCK_STRING))
                .weatherDescriptions(List.of(MOCK_STRING))
                .windSpeed(0)
                .windDegree(0)
                .windDir(MOCK_STRING)
                .pressure(0)
                .humidity(0)
                .cloudcover(0)
                .feelslike(0)
                .uvIndex(0)
                .visibility(0));
  }

  public static Articles mockArticles() {
    return new Articles()
        .pagination(new ArticlesPagination().limit(0).offset(0).count(0).total(0))
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
                    .publishedAt(MOCK_ISO8601_DATE)));
  }

  public static SpringDataSortInfo mockSpringDataSortInfo() {
    return new SpringDataSortInfo().empty(true).sorted(true).unsorted(true);
  }

  public static Comments mockComments() {
    return new Comments()
        .content(List.of(new CommentsContent().content(MOCK_STRING).timestamp(MOCK_ISO8601_DATE)))
        .pageable(
            new SpringDataPageable()
                .sort(mockSpringDataSortInfo())
                .offset(0)
                .pageSize(0)
                .pageNumber(0)
                .paged(true)
                .unpaged(true))
        .last(true)
        .totalElements(0)
        .totalPages(0)
        .size(0)
        .number(0)
        .sort(mockSpringDataSortInfo())
        .first(true)
        .number(0)
        .empty(true);
  }

  public static CommentPostRequest mockCommentPostRequest() {
    return new CommentPostRequest().content(MOCK_STRING);
  }

  public static CommentPostResponse mockCommentPostResponse() {
    return new CommentPostResponse().content(MOCK_STRING).id(0).timestamp(MOCK_ISO8601_DATE);
  }
}
