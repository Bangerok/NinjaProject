package ru.bangerok.ninja.validation.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.RECORD_COMPONENT;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import ru.bangerok.ninja.rest.payload.request.RegisterRequest;
import ru.bangerok.ninja.validation.impl.PasswordMatchesValidator;

/**
 * <p> Meta-annotation with implementation, which can be used to validate the password when
 * registering for a match with re-entering the password. </p>
 * Used for example here: {@link RegisterRequest}.
 *
 * @author v.kuznetsov
 * @since 0.3.15
 */
@Documented
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Target({TYPE, ANNOTATION_TYPE, RECORD_COMPONENT})
@Retention(RUNTIME)
public @interface PasswordMatches {

  /**
   * Configurable error localization message with default value.
   *
   * @return localization message or default value.
   */
  String message() default "Matching error";

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