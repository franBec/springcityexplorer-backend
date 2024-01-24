package dev.pollito.springcityexplorer.util;

import static dev.pollito.springcityexplorer.util.Constants.SLF4J_MDC_SESSION_ID_KEY;

import dev.pollito.springcityexplorer.exception.WeatherException;
import dev.pollito.springcityexplorer.models.Error;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

public class ControllerAdviceUtil {

  private ControllerAdviceUtil() {}

  public static final String GENERIC_ERROR_MESSAGE =
      "An unexpected error occurred. Please try again later.";

  public static final String WEATHER_BAD_REQUEST_MESSAGE =
      "Looks like we took a wrong turn and couldn't find that city! Mind checking the map (spelling) again?";

  private static String getCurrentRequestPath() {
    ServletRequestAttributes attr =
        (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    return attr.getRequest().getRequestURI();
  }

  public static ResponseEntity<Error> getGenericError(Exception e) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
            new Error()
                .error(e.getClass().getSimpleName())
                .message(GENERIC_ERROR_MESSAGE)
                .path(getCurrentRequestPath())
                .timestamp(OffsetDateTime.now())
                .session(UUID.fromString(MDC.get(SLF4J_MDC_SESSION_ID_KEY))));
  }

  public static ResponseEntity<Error> getBadRequestError(
      MissingServletRequestParameterException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(
            new Error()
                .error(e.getClass().getSimpleName())
                .message(e.getBody().getDetail())
                .path(getCurrentRequestPath())
                .timestamp(OffsetDateTime.now())
                .session(UUID.fromString(MDC.get(SLF4J_MDC_SESSION_ID_KEY))));
  }

  public static ResponseEntity<Error> getBadRequestError(ConstraintViolationException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(
            new Error()
                .error(e.getClass().getSimpleName())
                .message(constraintViolationExceptionMessageFormatter(e))
                .path(getCurrentRequestPath())
                .timestamp(OffsetDateTime.now())
                .session(UUID.fromString(MDC.get(SLF4J_MDC_SESSION_ID_KEY))));
  }

  private static String constraintViolationExceptionMessageFormatter(
      ConstraintViolationException e) {
    String formattedMessage = "";

    if (!e.getConstraintViolations().isEmpty()) {
      ConstraintViolation<?> violation = e.getConstraintViolations().iterator().next();
      String propertyPath = violation.getPropertyPath().toString();
      String fieldName = propertyPath.substring(propertyPath.lastIndexOf('.') + 1);
      String message = violation.getMessage();
      formattedMessage = fieldName + ": " + message;
    }

    return formattedMessage;
  }

  public static ResponseEntity<Error> getBadRequestError(MethodArgumentTypeMismatchException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(
            new Error()
                .error(e.getClass().getSimpleName())
                .message(e.getCause().getCause().getMessage())
                .path(getCurrentRequestPath())
                .timestamp(OffsetDateTime.now())
                .session(UUID.fromString(MDC.get(SLF4J_MDC_SESSION_ID_KEY))));
  }

  public static ResponseEntity<Error> getBadRequestError(MethodArgumentNotValidException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(
            new Error()
                .error(e.getClass().getSimpleName())
                .message(methodArgumentNotValidExceptionMessageFormatter(e))
                .path(getCurrentRequestPath())
                .timestamp(OffsetDateTime.now())
                .session(UUID.fromString(MDC.get(SLF4J_MDC_SESSION_ID_KEY))));
  }

  @NotNull
  private static String methodArgumentNotValidExceptionMessageFormatter(
      MethodArgumentNotValidException e) {
    return Objects.requireNonNull(e.getDetailMessageArguments())[1]
        .toString()
        .replace("[", "")
        .replace("]", "");
  }

  public static ResponseEntity<Error> getWeatherBadRequestError(WeatherException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(
            new Error()
                .error(e.getClass().getSimpleName())
                .message(WEATHER_BAD_REQUEST_MESSAGE)
                .path(getCurrentRequestPath())
                .timestamp(OffsetDateTime.now())
                .session(UUID.fromString(MDC.get(SLF4J_MDC_SESSION_ID_KEY))));
  }
}
