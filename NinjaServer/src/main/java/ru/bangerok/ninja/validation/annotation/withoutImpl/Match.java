package ru.bangerok.ninja.validation.annotation.withoutImpl;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import ru.bangerok.ninja.controller.payload.request.RegisterRequest;

/**
 * Мета-аннотация без имплементации, которая может быть использована для проверки повторного ввода
 * пароля.
 * <p>
 * Используется, например, здесь: {@link RegisterRequest}.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
@Documented
@Target({FIELD})
@Retention(RUNTIME)
public @interface Match {

		String message() default "Dont match passwords";
}