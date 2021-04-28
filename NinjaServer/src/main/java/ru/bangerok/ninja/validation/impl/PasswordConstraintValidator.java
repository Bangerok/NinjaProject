package ru.bangerok.ninja.validation.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.passay.AlphabeticalCharacterRule;
import org.passay.DigitCharacterRule;
import org.passay.LengthRule;
import org.passay.LowercaseCharacterRule;
import org.passay.MessageResolver;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.PropertiesMessageResolver;
import org.passay.RuleResult;
import org.passay.SpecialCharacterRule;
import org.passay.UppercaseCharacterRule;
import org.passay.WhitespaceRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

		private static final Logger LOGGER = LoggerFactory.getLogger(PasswordConstraintValidator.class);

		@Override
		public void initialize(ValidPassword arg0) {
		}

		@Override
		public boolean isValid(String password, ConstraintValidatorContext context) {
				// @formatter:off
				PasswordValidator validator = new PasswordValidator(getMessageResolver(), Arrays.asList(
						new LengthRule(8, 16),
						new UppercaseCharacterRule(1),
						new LowercaseCharacterRule(1),
						new DigitCharacterRule(1),
						new SpecialCharacterRule(1),
						new AlphabeticalCharacterRule(1),
						new WhitespaceRule())
				);

				RuleResult result = validator.validate(new PasswordData(password));
				if (result.isValid()) {
						return true;
				}

				context.disableDefaultConstraintViolation();
				validator.getMessages(result)
						.forEach(m -> context.buildConstraintViolationWithTemplate(m).addConstraintViolation());
				return false;
		}

		/**
		 * Получение кастомных сообщений для валидации пароля.
		 *
		 * @return MessageResolver с данными о новом списке сообщений, которые будут использованы для
		 * сообщений об ошибке валидации.
		 */
		private MessageResolver getMessageResolver() {
				Properties props = new Properties();
				InputStream inputStream = getClass().getClassLoader()
						.getResourceAsStream("messages/password.properties");

				try {
						props.load(inputStream);
				} catch (IOException e) {
						LOGGER.error("Error load password properties file", e);
				}

				return new PropertiesMessageResolver(props);
		}
}