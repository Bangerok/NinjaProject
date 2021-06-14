package ru.bangerok.ninja.validation.impl;

import java.lang.reflect.Field;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import ru.bangerok.ninja.rest.payload.request.RegisterRequest;
import ru.bangerok.ninja.validation.annotation.PasswordMatches;
import ru.bangerok.ninja.validation.annotation.single.Match;

/**
 * <p> Validating class for annotation - validation of entered passwords during user registration
 * . </p>
 * Connects here: {@link PasswordMatches}.
 *
 * @author v.kuznetsov
 * @since 0.3.15
 */
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

  @Override
  public void initialize(PasswordMatches constraintAnnotation) {
  }

  /**
   * Method-validator of passwords entered by the user for a match.
   *
   * @param obj     registration object for validation.
   * @param context validator context.
   * @return true if the passwords match, otherwise false.
   */
  @Override
  public boolean isValid(Object obj, ConstraintValidatorContext context) {
    RegisterRequest registerRequest = (RegisterRequest) obj;
    boolean result = registerRequest.password()
        .equals(registerRequest.matchingPassword());
    if (!result) {
      for (Field field : obj.getClass().getDeclaredFields()) {
        if (field.isAnnotationPresent(Match.class)) {
          context.disableDefaultConstraintViolation();
          context.buildConstraintViolationWithTemplate(
              field.getAnnotation(Match.class).message()
          ).addPropertyNode(field.getName()).addConstraintViolation();
          break;
        }
      }
    }

    return result;
  }
}