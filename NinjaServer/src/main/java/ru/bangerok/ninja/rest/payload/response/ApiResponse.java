package ru.bangerok.ninja.rest.payload.response;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import ru.bangerok.ninja.rest.controllers.auth.AuthController;

/**
 * <p> Payload java record to send response from server in controller. </p>
 * Used for example here: {@link AuthController#registerUser}.
 *
 * @author v.kuznetsov
 * @since 0.4.5
 */
public record ApiResponse(

    // Private field containing a message about the final result of the request.
    String message,

    // Private field containing any data to be processed on the client.
    Object data,

    // Private field containing query execution errors.
    String error) {

  public ApiResponse(String message) {
    this(message, null, null);
  }

  public ApiResponse(Object data) {
    this(null, data, null);
  }

  /**
   * Formation of a response from the server after field validation.
   *
   * @param allErrors list of validation errors.
   * @param error     error name.
   */
  public ApiResponse(List<ObjectError> allErrors, String error) {
    this("[" + getAllErrorsMessages(allErrors) + "]", null, error);
  }

  private static String getAllErrorsMessages(List<ObjectError> allErrors) {
    return allErrors.parallelStream().map(e -> {
      if (e instanceof FieldError fieldError) {
        return "{\"field\":\"" + fieldError.getField() + "\",\"defaultMessage\":\""
            + e.getDefaultMessage() + "\"}";
      } else {
        return "{\"object\":\"" + e.getObjectName() + "\",\"defaultMessage\":\"" + e
            .getDefaultMessage() + "\"}";
      }
    }).collect(Collectors.joining(","));
  }
}