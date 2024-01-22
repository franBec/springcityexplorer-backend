package dev.pollito.springcityexplorer.decoder;

import static com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.weatherstack.models.WeatherStackError;
import dev.pollito.springcityexplorer.exception.WeatherException;
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
  public Object decode(Response response, Type type) throws IOException {
    try (BufferedReader reader =
        new BufferedReader(
            new InputStreamReader(response.body().asInputStream(), StandardCharsets.UTF_8))) {

      String responseBody = reader.lines().collect(Collectors.joining());

      WeatherStackError error = new Gson().fromJson(responseBody, WeatherStackError.class);
      if (error != null && Boolean.FALSE.equals(error.getSuccess())) {
        throw new WeatherException(error);
      }

      return new GsonBuilder()
          .setFieldNamingPolicy(LOWER_CASE_WITH_UNDERSCORES)
          .create()
          .fromJson(responseBody, type);
    }
  }
}
