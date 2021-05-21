package ru.bangerok.ninja.validation.annotation.withoutImpl;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import ru.bangerok.ninja.rest.payload.request.RegisterRequest;

/**
 * An unimplemented meta annotation that can be used to validate password re-entry.
 * <p>
 * Used for example here: {@link RegisterRequest}.
 *
 * @author v.kuznetsov
 * @since 0.4.15
 */
@Documented
@Target({FIELD})
@Retention(RUNTIME)
public @interface Match {

		String message() default "Dont match passwords";
}