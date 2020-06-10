package ru.bangerok.ninja.validation.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import ru.bangerok.ninja.validation.annotation.ValidPassword;

/**
 * Валидирующий класс для аннотации - валидации введенного пароля пользователем при регистрации.
 * <p>
 * Подключается здесь: {@link ValidPassword}.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

		@Override
		public void initialize(ValidPassword arg0) {

		}

		@Override
		public boolean isValid(String password, ConstraintValidatorContext context) {
				// @formatter:off
				/*PasswordValidator validator = new PasswordValidator(Arrays.asList(
						new LengthRule(8, 30),
						new UppercaseCharacterRule(1),
						new DigitCharacterRule(1),
						new SpecialCharacterRule(1),
						new NumericalSequenceRule(3, false),
						new AlphabeticalSequenceRule(3, false),
						new QwertySequenceRule(3, false),
						new WhitespaceRule())
				);
				
				RuleResult result = validator.validate(new PasswordData(password));
				if (result.isValid()) {
						return true;
				}
				
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(
						Joiner.on(",").join(validator.getMessages(result))).addConstraintViolation();
				return false;*/

				return true;
		}

}
