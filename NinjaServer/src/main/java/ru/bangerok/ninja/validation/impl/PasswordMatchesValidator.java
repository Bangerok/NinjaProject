package ru.bangerok.ninja.validation.impl;

import java.lang.reflect.Field;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import ru.bangerok.ninja.controller.payload.request.RegisterRequest;
import ru.bangerok.ninja.validation.annotation.PasswordMatches;
import ru.bangerok.ninja.validation.annotation.withoutImpl.Match;

/**
 * Валидирующий класс для аннотации - проверки введенных паролей при регистрации пользователем.
 * <p>
 * Подключается здесь: {@link PasswordMatches}.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

		@Override
		public void initialize(PasswordMatches constraintAnnotation) {
		}

		/**
		 * Метод-валидатор введенных паролей пользователем на совпадение.
		 *
		 * @param obj     объект регистрации для валидации.
		 * @param context контекст валидатора.
		 * @return true, если пароли совпадают, иначе false.
		 */
		@Override
		public boolean isValid(Object obj, ConstraintValidatorContext context) {
				RegisterRequest registerRequest = (RegisterRequest) obj;
				boolean result = registerRequest.getPassword()
						.equals(registerRequest.getMatchingPassword());
				if (!result) {
						for (Field field : obj.getClass().getDeclaredFields()) {
								if (field.isAnnotationPresent(Match.class)) {
										context.disableDefaultConstraintViolation();
										context.buildConstraintViolationWithTemplate(
												field.getAnnotation(Match.class).message()
										).addPropertyNode(field.getName()).addConstraintViolation();
										break;
								}
						}
				}

				return result;
		}
}