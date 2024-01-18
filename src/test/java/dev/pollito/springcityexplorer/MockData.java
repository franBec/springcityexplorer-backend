package dev.pollito.springcityexplorer;

import com.weatherstack.models.Current;
import com.weatherstack.models.Location;
import com.weatherstack.models.LocationTypeEnum;
import com.weatherstack.models.Request;
import com.weatherstack.models.UnitEnum;
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
  public static final String WEATHER_REQUEST_LANGUAGE = faker.nation().language();
  public static final String WEATHER_REQUEST_QUERY = faker.address().fullAddress();
  public static final float WEATHER_CURRENT_PRECIP = (float) faker.number().randomDouble(1, 0, 1);
  public static final int WEATHER_CURRENT_VISIBILITY = faker.number().numberBetween(1, 10);
  public static final int WEATHER_CURRENT_UV_INDEX = faker.number().numberBetween(0, 8);
  public static final int WEATHER_CURRENT_FEELSLIKE = faker.number().numberBetween(-20, 50);
  public static final int WEATHER_CURRENT_CLOUDCOVER = faker.number().numberBetween(0, 100);
  public static final int WEATHER_CURRENT_HUMIDITY = faker.number().numberBetween(0, 100);
  public static final int WEATHER_CURRENT_PRESSURE = faker.number().numberBetween(950, 1050);
  public static final String WEATHER_CURRENT_WIND_DIR = faker.regexify("[A-Z]{3}");
  public static final int WEATHER_CURRENT_WIND_DEGREE = faker.number().numberBetween(0, 359);
  public static final int WEATHER_CURRENT_WIND_SPEED = faker.number().numberBetween(0, 70);
  public static final List<String> WEATHER_CURRENT_WEATHER_DESCRIPTIONS =
      List.of(faker.weather().description(), faker.weather().description());
  public static final List<String> WEATHER_CURRENT_WEATHER_ICONS =
      List.of(faker.internet().image(), faker.internet().image());
  public static final int WEATHER_CURRENT_WEATHER_CODE = (int) faker.number().randomNumber(3, true);
  public static final int WEATHER_CURRENT_TEMPERATURE = faker.number().numberBetween(-20, 50);
  public static final String WEATHER_CURRENT_OBSERVATION_TIME =
      new SimpleDateFormat("hh:mm a").format(faker.date().future(365, TimeUnit.DAYS));
  public static final String WEATHER_LOCATION_UTC_OFFSET =
      String.valueOf(faker.number().numberBetween(-12, 14));
  public static final int WEATHER_LOCATION_LOCALTIME_EPOCH =
      (int) faker.number().randomNumber(10, true);
  public static final String WEATHER_LOCATION_LOCALTIME =
      new SimpleDateFormat("yyyy-MM-dd HH:mm").format(faker.date().future(365, TimeUnit.DAYS));
  public static final String WEATHER_LOCATION_TIMEZONE = faker.address().timeZone();
  public static final String WEATHER_LOCATION_LONGITUDE = faker.address().longitude();
  public static final String WEATHER_LOCATION_LATITUDE = faker.address().latitude();
  public static final String WEATHER_LOCATION_REGION = faker.coffee().region();
  public static final String WEATHER_LOCATION_COUNTRY = faker.address().country();
  public static final String WEATHER_LOCATION_CITY = faker.address().city();

  public static Weather mockWeather() {
    return new Weather()
        .location(
            new WeatherLocation()
                .name(WEATHER_LOCATION_CITY)
                .country(WEATHER_LOCATION_COUNTRY)
                .region(WEATHER_LOCATION_REGION)
                .lat(WEATHER_LOCATION_LATITUDE)
                .lon(WEATHER_LOCATION_LONGITUDE)
                .timezoneId(WEATHER_LOCATION_TIMEZONE)
                ._localtime(WEATHER_LOCATION_LOCALTIME)
                .localtimeEpoch(WEATHER_LOCATION_LOCALTIME_EPOCH)
                .utcOffset(WEATHER_LOCATION_UTC_OFFSET))
        .current(
            new WeatherCurrent()
                .observationTime(WEATHER_CURRENT_OBSERVATION_TIME)
                .temperature(WEATHER_CURRENT_TEMPERATURE)
                .weatherCode(WEATHER_CURRENT_WEATHER_CODE)
                .weatherIcons(WEATHER_CURRENT_WEATHER_ICONS)
                .weatherDescriptions(WEATHER_CURRENT_WEATHER_DESCRIPTIONS)
                .windSpeed(WEATHER_CURRENT_WIND_SPEED)
                .windDegree(WEATHER_CURRENT_WIND_DEGREE)
                .windDir(WEATHER_CURRENT_WIND_DIR)
                .pressure(WEATHER_CURRENT_PRESSURE)
                .precip(WEATHER_CURRENT_PRECIP)
                .humidity(WEATHER_CURRENT_HUMIDITY)
                .cloudcover(WEATHER_CURRENT_CLOUDCOVER)
                .feelslike(WEATHER_CURRENT_FEELSLIKE)
                .uvIndex(WEATHER_CURRENT_UV_INDEX)
                .visibility(WEATHER_CURRENT_VISIBILITY));
  }

  public static com.weatherstack.models.Weather mockWeatherstackWeather() {
    return new com.weatherstack.models.Weather()
        .request(
            new Request()
                .type(LocationTypeEnum.CITY)
                .query(WEATHER_REQUEST_QUERY)
                .language(WEATHER_REQUEST_LANGUAGE)
                .unit(UnitEnum.M))
        .location(
            new Location()
                .name(WEATHER_LOCATION_CITY)
                .country(WEATHER_LOCATION_COUNTRY)
                .region(WEATHER_LOCATION_REGION)
                .lat(WEATHER_LOCATION_LATITUDE)
                .lon(WEATHER_LOCATION_LONGITUDE)
                .timezoneId(WEATHER_LOCATION_TIMEZONE)
                ._localtime(WEATHER_LOCATION_LOCALTIME)
                .localtimeEpoch(WEATHER_LOCATION_LOCALTIME_EPOCH)
                .utcOffset(WEATHER_LOCATION_UTC_OFFSET))
        .current(
            new Current()
                .observationTime(WEATHER_CURRENT_OBSERVATION_TIME)
                .temperature(WEATHER_CURRENT_TEMPERATURE)
                .weatherCode(WEATHER_CURRENT_WEATHER_CODE)
                .weatherIcons(WEATHER_CURRENT_WEATHER_ICONS)
                .weatherDescriptions(WEATHER_CURRENT_WEATHER_DESCRIPTIONS)
                .windSpeed(WEATHER_CURRENT_WIND_SPEED)
                .windDegree(WEATHER_CURRENT_WIND_DEGREE)
                .windDir(WEATHER_CURRENT_WIND_DIR)
                .pressure(WEATHER_CURRENT_PRESSURE)
                .precip(WEATHER_CURRENT_PRECIP)
                .humidity(WEATHER_CURRENT_HUMIDITY)
                .cloudcover(WEATHER_CURRENT_CLOUDCOVER)
                .feelslike(WEATHER_CURRENT_FEELSLIKE)
                .uvIndex(WEATHER_CURRENT_UV_INDEX)
                .visibility(WEATHER_CURRENT_VISIBILITY));
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
                    .image(faker.internet().image())
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
