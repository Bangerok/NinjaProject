package ru.bangerok.ninja.exception.email;

import ru.bangerok.ninja.service.impl.MailServiceImpl;

/**
 * <p> An exception class for handling unsuccessful email messages. </p>
 * Used for example here: {@link MailServiceImpl}.
 *
 * @author v.kuznetsov
 * @since 0.5.7
 */
public final class FailureSendEmailException extends RuntimeException {

  public FailureSendEmailException(final String message) {
    super(message);
  }
}