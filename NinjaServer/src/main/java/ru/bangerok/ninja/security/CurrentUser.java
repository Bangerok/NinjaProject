package ru.bangerok.ninja.security;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import ru.bangerok.ninja.controller.AuthController;

/**
 * Мета-аннотация, которая может быть использована для вставки аутентифицированного в данный момент
 * субъекта пользователя в контроллеры.
 * <p>
 * Используется, например, здесь: {@link AuthController#getCurrentUser(UserPrincipal)}.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
@Target({ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal
public @interface CurrentUser {

}