package ru.bangerok.ninja.validation.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;
import ru.bangerok.ninja.validation.annotation.ValidEmail;

/**
 * Валидирующий класс для аннотации - проверки электронной почты по паттерну.
 * <p>
 * Подключается здесь: {@link ValidEmail}.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

		/**
		 * Паттерн для проверки электронной почты.
		 */
		private static final String EMAIL_PATTERN =
				"^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";

		@Override
		public void initialize(ValidEmail constraintAnnotation) {
		}

		/**
		 * Метод-валидатор электронной почты.
		 *
		 * @param email   электронная почта для валидации.
		 * @param context контекст валидатора.
		 * @return true, если электронная почта валидна, иначе false.
		 */
		@Override
		public boolean isValid(String email, ConstraintValidatorContext context) {
				return (validateEmail(email));
		}

		/**
		 * Метод для проверки электронной почты по reg-exp паттерну.
		 *
		 * @param email электронная почта для проверки.
		 * @return true, если электронная почта прошла проверку, иначе false.
		 */
		private boolean validateEmail(String email) {
				if (!StringUtils.hasText(email)) {
						return true;
				}

				Pattern pattern = Pattern.compile(EMAIL_PATTERN);
				Matcher matcher = pattern.matcher(email);
				return matcher.matches();
		}
}