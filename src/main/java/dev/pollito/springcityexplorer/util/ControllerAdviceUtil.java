package dev.pollito.springcityexplorer.util;

import dev.pollito.springcityexplorer.exception.WeatherException;
import dev.pollito.springcityexplorer.models.Error;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ControllerAdviceUtil {

  private ControllerAdviceUtil() {}

  public static final String GENERIC_ERROR_MESSAGE = "An unexpected error occurred. Please try again later.";

  public static final String WEATHER_BAD_REQUEST_MESSAGE =
      "Looks like we took a wrong turn and couldn't find that city! Mind checking the map (spelling) again?";

  public static ResponseEntity<Error> getGenericError(Exception e) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
            new Error()
                .error(e.getClass().getSimpleName())
                .message(GENERIC_ERROR_MESSAGE)
                .method("N/A")
                .timestamp(OffsetDateTime.now())
                .session(UUID.randomUUID()));
  }

  public static ResponseEntity<Error> getBadRequestError(String error, String message){
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(
                    new Error()
                            .error(error)
                            .message(message)
                            .method("N/A")
                            .timestamp(OffsetDateTime.now())
                            .session(UUID.randomUUID()));
  }

  public static ResponseEntity<Error> getWeatherBadRequestError(WeatherException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(
            new Error()
                .error(e.getClass().getSimpleName())
                .message(WEATHER_BAD_REQUEST_MESSAGE)
                .method("N/A")
                .timestamp(OffsetDateTime.now())
                .session(UUID.randomUUID()));
  }
}
