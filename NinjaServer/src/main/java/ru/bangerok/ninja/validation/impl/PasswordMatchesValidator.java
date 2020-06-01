package ru.bangerok.ninja.validation.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import ru.bangerok.ninja.controller.payload.request.RegisterRequest;
import ru.bangerok.ninja.validation.annotation.PasswordMatches;

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
		 * Метод-валидатор введенных паролей пользователем.
		 *
		 * @param obj     объект регистрации для валидации.
		 * @param context контекст валидатора.
		 * @return true, если пароли совпадают, иначе false.
		 */
		@Override
		public boolean isValid(Object obj, ConstraintValidatorContext context) {
				RegisterRequest registerRequest = (RegisterRequest) obj;
				return registerRequest.getPassword().equals(registerRequest.getMatchingPassword());
		}
}
