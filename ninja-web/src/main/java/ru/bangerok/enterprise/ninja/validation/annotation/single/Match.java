package ru.bangerok.enterprise.ninja.validation.annotation.single;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <p> An unimplemented meta annotation that can be used to validate password re-entry. </p>
 *
 * @author Vladislav [Bangerok] Kuznetsov.
 * @since 0.4.15
 */
@Documented
@Target({FIELD})
@Retention(RUNTIME)
public @interface Match {

  /**
   * Configurable error localization message with default value.
   *
   * @return localization message or default value.
   */
  String message() default "Dont match passwords";
}