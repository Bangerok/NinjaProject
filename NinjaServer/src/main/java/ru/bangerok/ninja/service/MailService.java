package ru.bangerok.ninja.service;

import ru.bangerok.ninja.event.listener.RegistrationListener;
import ru.bangerok.ninja.exception.email.FailureSendEmailException;

/**
 * <p> Interface for working with sending letters to email. </p>
 * Used for example here: {@link RegistrationListener}.
 *
 * @author v.kuznetsov
 * @since 0.4.3
 */
public interface MailService {

  /**
   * Method for sending verification email.
   *
   * @param toEmail where we send.
   * @param token   verification token issued to the user to verify email.
   * @throws FailureSendEmailException unsuccessful email message sending.
   */
  void sendVerifiedMessage(String toEmail, String token) throws FailureSendEmailException;
}