package dev.pollito.springcityexplorer.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.weatherstack.models.Current;
import com.weatherstack.models.Location;
import com.weatherstack.models.LocationTypeEnum;
import com.weatherstack.models.Request;
import com.weatherstack.models.UnitEnum;
import com.weatherstack.models.Weather;
import java.lang.reflect.Type;
import java.util.Arrays;

public class WeatherDeserializer implements JsonDeserializer<Weather> {
  @Override
  public Weather deserialize(
      JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext)
      throws JsonParseException {
    JsonObject jsonObject = jsonElement.getAsJsonObject();
    JsonObject requestObj = jsonObject.getAsJsonObject("request");
    JsonObject locationObj = jsonObject.getAsJsonObject("location");
    JsonObject currentObj = jsonObject.getAsJsonObject("current");

    return new Weather()
        .request(
            new Request()
                .type(LocationTypeEnum.fromValue(requestObj.get("type").getAsString()))
                .query(requestObj.get("query").getAsString())
                .language(requestObj.get("language").getAsString())
                .unit(UnitEnum.fromValue(requestObj.get("unit").getAsString())))
        .location(
            new Location()
                .name(locationObj.get("name").getAsString())
                .country(locationObj.get("country").getAsString())
                .region(locationObj.get("region").getAsString())
                .lat(locationObj.get("lat").getAsString())
                .lon(locationObj.get("lon").getAsString())
                .timezoneId(locationObj.get("timezone_id").getAsString())
                ._localtime(locationObj.get("localtime").getAsString())
                .localtimeEpoch(locationObj.get("localtime_epoch").getAsInt())
                .utcOffset(locationObj.get("utc_offset").getAsString()))
        .current(
            new Current()
                .observationTime(currentObj.get("observation_time").getAsString())
                .temperature(currentObj.get("temperature").getAsInt())
                .weatherCode(currentObj.get("weather_code").getAsInt())
                .weatherIcons(
                    Arrays.asList(
                        jsonDeserializationContext.deserialize(
                            currentObj.get("weather_icons"), String[].class)))
                .weatherDescriptions(
                    Arrays.asList(
                        jsonDeserializationContext.deserialize(
                            currentObj.get("weather_descriptions"), String[].class)))
                .windSpeed(currentObj.get("wind_speed").getAsInt())
                .windDegree(currentObj.get("wind_degree").getAsInt())
                .windDir(currentObj.get("wind_dir").getAsString())
                .pressure(currentObj.get("pressure").getAsInt())
                .precip(currentObj.get("precip").getAsFloat())
                .humidity(currentObj.get("humidity").getAsInt())
                .cloudcover(currentObj.get("cloudcover").getAsInt())
                .feelslike(currentObj.get("feelslike").getAsInt())
                .uvIndex(currentObj.get("uv_index").getAsInt())
                .visibility(currentObj.get("visibility").getAsInt()));
  }
}
