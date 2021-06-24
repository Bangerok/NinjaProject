package ru.bangerok.ninja.exception.email;

/**
 * <p> An exception class for handling unsuccessful email messages. </p>
 *
 * @author v.kuznetsov
 * @since 0.5.7
 */
public final class FailureSendEmailException extends RuntimeException {

  public FailureSendEmailException(final String message) {
    super(message);
  }
}