package ru.bangerok.ninja.validation.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import ru.bangerok.ninja.rest.payload.request.RegisterRequest;
import ru.bangerok.ninja.validation.impl.PasswordConstraintValidator;

/**
 * <p> Meta-annotation with implementation, which can be used to validate the password when
 * registering to match the filling rules. </p>
 * Used for example here: {@link RegisterRequest}.
 *
 * @author v.kuznetsov
 * @since 0.4.5
 */
@Documented
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface ValidPassword {

  /**
   * Configurable error localization message with default value.
   *
   * @return localization message or default value.
   */
  String message() default "Invalid Password";

  /**
   * Allows you to determine under what circumstances this check will be triggered.
   *
   * @return array groups or default value.
   */
  Class<?>[] groups() default {};

  /**
   * Allows you to define the payload that will be transmitted with verification.
   *
   * @return array payloads or default value.
   */
  Class<? extends Payload>[] payload() default {};
}