package dev.pollito.springcityexplorer;

import dev.pollito.springcityexplorer.models.Article;
import dev.pollito.springcityexplorer.models.Articles;
import dev.pollito.springcityexplorer.models.ArticlesPagination;
import dev.pollito.springcityexplorer.models.Comment;
import dev.pollito.springcityexplorer.models.CommentPostRequest;
import dev.pollito.springcityexplorer.models.CommentPostResponse;
import dev.pollito.springcityexplorer.models.Comments;
import dev.pollito.springcityexplorer.models.CountryEnum;
import dev.pollito.springcityexplorer.models.SpringDataPageable;
import dev.pollito.springcityexplorer.models.SpringDataSortInfo;
import dev.pollito.springcityexplorer.models.Weather;
import dev.pollito.springcityexplorer.models.WeatherCurrent;
import dev.pollito.springcityexplorer.models.WeatherLocation;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;
import net.datafaker.Faker;

public class MockData {
  private MockData() {}

  private static final Faker faker = new Faker();

  public static Weather mockWeather() {
    return new Weather()
        .location(
            new WeatherLocation()
                .name(faker.address().city())
                .country(faker.address().country())
                .region(faker.coffee().region())
                .lat(faker.address().latitude())
                .lon(faker.address().longitude())
                .timezoneId(faker.address().timeZone())
                ._localtime(
                    new SimpleDateFormat("yyyy-MM-dd HH:mm")
                        .format(faker.date().future(365, TimeUnit.DAYS)))
                .localtimeEpoch((int) faker.number().randomNumber(10, true))
                .utcOffset(String.valueOf(faker.number().numberBetween(-12, 14))))
        .current(
            new WeatherCurrent()
                .observationTime(
                    new SimpleDateFormat("hh:mm a").format(faker.date().future(365, TimeUnit.DAYS)))
                .temperature(faker.number().numberBetween(-20, 50))
                .weatherCode((int) faker.number().randomNumber(3, true))
                .weatherIcons(List.of(faker.internet().image()))
                .weatherDescriptions(List.of(faker.weather().description()))
                .windSpeed(faker.number().numberBetween(0, 70))
                .windDegree(faker.number().numberBetween(0, 359))
                .windDir(faker.regexify("[A-Z]{3}"))
                .pressure(faker.number().numberBetween(950, 1050))
                .humidity(faker.number().numberBetween(0, 100))
                .cloudcover(faker.number().numberBetween(0, 100))
                .feelslike(faker.number().numberBetween(-20, 50))
                .uvIndex(faker.number().numberBetween(0, 8))
                .visibility(faker.number().numberBetween(1, 10)));
  }

  public static Articles mockArticles() {
    return new Articles()
        .pagination(new ArticlesPagination().limit(0).offset(0).count(0).total(0))
        .data(
            List.of(
                new Article()
                    .author(faker.book().author())
                    .title(faker.book().title())
                    .description(faker.coffee().descriptor())
                    .url(faker.internet().url())
                    .image(faker.internet().url())
                    .category(Article.CategoryEnum.GENERAL)
                    .language(Article.LanguageEnum.AR)
                    .country(CountryEnum.AR)
                    .publishedAt(OffsetDateTime.now())));
  }

  public static SpringDataSortInfo mockSpringDataSortInfo() {
    return new SpringDataSortInfo()
        .empty(faker.random().nextBoolean())
        .sorted(faker.random().nextBoolean())
        .unsorted(faker.random().nextBoolean());
  }

  public static Comments mockComments() {
    return new Comments()
        .content(
            List.of(
                new Comment()
                    .content(faker.lorem().characters(faker.number().numberBetween(1, 200)))
                    .timestamp(OffsetDateTime.now())))
        .pageable(
            new SpringDataPageable()
                .sort(mockSpringDataSortInfo())
                .offset(faker.number().positive())
                .pageSize(faker.number().positive())
                .pageNumber(faker.number().positive())
                .paged(faker.random().nextBoolean())
                .unpaged(faker.random().nextBoolean()))
        .last(faker.random().nextBoolean())
        .totalElements(faker.number().positive())
        .totalPages(faker.number().positive())
        .size(faker.number().positive())
        .number(faker.number().positive())
        .sort(mockSpringDataSortInfo())
        .first(faker.random().nextBoolean())
        .number(faker.number().positive())
        .empty(faker.random().nextBoolean());
  }

  public static CommentPostRequest mockCommentPostRequest() {
    return new CommentPostRequest()
        .content(faker.lorem().characters(faker.number().numberBetween(1, 200)));
  }

  public static CommentPostResponse mockCommentPostResponse() {
    return new CommentPostResponse()
        .content(faker.lorem().characters(faker.number().numberBetween(1, 200)))
        .id(0)
        .timestamp(OffsetDateTime.now());
  }
}
