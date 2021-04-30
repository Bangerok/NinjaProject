package ru.bangerok.ninja.validation.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import ru.bangerok.ninja.controller.payload.request.RegisterRequest;
import ru.bangerok.ninja.validation.impl.PasswordMatchesValidator;

/**
 * Meta-annotation with implementation, which can be used to validate the password when registering
 * for a match with re-entering the password.
 * <p>
 * Used for example here: {@link RegisterRequest}.
 *
 * @author v.kuznetsov
 * @since 0.3.15
 */
@Documented
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface PasswordMatches {

		String message() default "Matching error";

		Class<?>[] groups() default {};

		Class<? extends Payload>[] payload() default {};
}