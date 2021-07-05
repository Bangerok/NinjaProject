package ru.bangerok.enterprise.ninja.validation.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import ru.bangerok.enterprise.ninja.validation.impl.EmailValidator;

/**
 * <p> Meta-annotation with implementation that can be used to validate email. </p>
 *
 * @author Vladislav [Bangerok] Kuznetsov.
 * @since 0.3.15
 */
@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
@Documented
public @interface ValidEmail {

  /**
   * Configurable error localization message with default value.
   *
   * @return localization message or default value.
   */
  String message() default "Invalid email";

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