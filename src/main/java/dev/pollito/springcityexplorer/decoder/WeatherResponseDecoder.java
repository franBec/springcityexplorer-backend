package dev.pollito.springcityexplorer.decoder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.weatherstack.models.Weather;
import com.weatherstack.models.WeatherStackError;
import dev.pollito.springcityexplorer.deserializer.WeatherDeserializer;
import dev.pollito.springcityexplorer.exception.WeatherException;
import feign.FeignException;
import feign.Response;
import feign.codec.Decoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class WeatherResponseDecoder implements Decoder {

  @Override
  public Object decode(Response response, Type type) throws IOException, FeignException {
    try (BufferedReader reader =
        new BufferedReader(
            new InputStreamReader(response.body().asInputStream(), StandardCharsets.UTF_8))) {

      String responseBody = reader.lines().collect(Collectors.joining());

      WeatherStackError error = new Gson().fromJson(responseBody, WeatherStackError.class);
      if (error != null && Boolean.FALSE.equals(error.getSuccess())) {
        throw new WeatherException(error);
      }

      return new GsonBuilder()
          .registerTypeAdapter(Weather.class, new WeatherDeserializer())
          .create()
          .fromJson(responseBody, type);
    }
  }
}
