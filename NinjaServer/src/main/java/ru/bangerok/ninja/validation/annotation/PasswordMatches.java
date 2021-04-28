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
 * Мета-аннотация с имплементацией, которая может быть использована для валидации пароля при
 * регистрации на совпадение с повторным вводом пароля.
 * <p>
 * Используется, например, здесь: {@link RegisterRequest}.
 *
 * @author v.kuznetsov
 * @version 1.0
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