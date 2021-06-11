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
import ru.bangerok.ninja.validation.impl.EmailValidator;

/**
 * Meta-annotation with implementation that can be used to validate email.
 * <p>
 * Used for example here: {@link RegisterRequest#getEmail()}.
 *
 * @author v.kuznetsov
 * @since 0.3.15
 */
@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
@Documented
public @interface ValidEmail {

  String message() default "Invalid email";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}