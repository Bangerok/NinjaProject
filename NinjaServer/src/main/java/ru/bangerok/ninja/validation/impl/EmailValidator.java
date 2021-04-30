package ru.bangerok.ninja.validation.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;
import ru.bangerok.ninja.validation.annotation.ValidEmail;

/**
 * Validating class for annotation - pattern-based email validation.
 * <p>
 * Connects here: {@link ValidEmail}.
 *
 * @author v.kuznetsov
 * @since 0.3.15
 */
public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

		/**
		 * Pattern for checking email.
		 */
		private static final String EMAIL_PATTERN =
				"^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";

		@Override
		public void initialize(ValidEmail constraintAnnotation) {
		}

		/**
		 * Email validator method.
		 *
		 * @param email   email for validation.
		 * @param context validator context.
		 * @return true if the email is valid, otherwise false.
		 */
		@Override
		public boolean isValid(String email, ConstraintValidatorContext context) {
				return (validateEmail(email));
		}

		/**
		 * Method for checking email using reg-exp pattern.
		 *
		 * @param email email for validation.
		 * @return true if the email passed validation, otherwise false.
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