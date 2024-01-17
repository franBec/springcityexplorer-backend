package dev.pollito.springcityexplorer.decoder;

import com.google.gson.Gson;
import com.weatherstack.models.WeatherStackError;
import dev.pollito.springcityexplorer.exception.WeatherException;
import feign.FeignException;
import feign.Response;
import feign.codec.Decoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

public class WeatherResponseDecoder implements Decoder {
  private final Gson gson = new Gson();

  @Override
  public Object decode(Response response, Type type) throws IOException, FeignException {
    try (BufferedReader reader =
        new BufferedReader(
            new InputStreamReader(response.body().asInputStream(), StandardCharsets.UTF_8))) {

      StringBuilder responseBody = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {
        responseBody.append(line);
      }

      WeatherStackError error = gson.fromJson(responseBody.toString(), WeatherStackError.class);
      if (error != null && Boolean.FALSE.equals(error.getSuccess())) {
        throw new WeatherException(error);
      }

      return gson.fromJson(responseBody.toString(), type);
    }
  }
}
