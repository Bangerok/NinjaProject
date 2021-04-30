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
import ru.bangerok.ninja.controller.payload.request.RegisterRequest;
import ru.bangerok.ninja.validation.impl.PasswordConstraintValidator;

/**
 * Meta-annotation with implementation, which can be used to validate the password when registering
 * to match the filling rules.
 * <p>
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

		String message() default "Invalid Password";

		Class<?>[] groups() default {};

		Class<? extends Payload>[] payload() default {};
}