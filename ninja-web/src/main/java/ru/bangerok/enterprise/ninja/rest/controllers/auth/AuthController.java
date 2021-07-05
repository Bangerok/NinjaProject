package ru.bangerok.enterprise.ninja.rest.controllers.auth;

import com.fasterxml.jackson.annotation.JsonView;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bangerok.enterprise.ninja.event.OnRegistrationCompleteEvent;
import ru.bangerok.enterprise.ninja.persistence.model.user.User;
import ru.bangerok.enterprise.ninja.persistence.model.views.Views;
import ru.bangerok.enterprise.ninja.rest.common.dto.ApiResponse;
import ru.bangerok.enterprise.ninja.rest.controllers.auth.dto.LoginDto;
import ru.bangerok.enterprise.ninja.rest.controllers.auth.dto.RegisterDto;
import ru.bangerok.enterprise.ninja.security.CurrentUser;
import ru.bangerok.enterprise.ninja.security.UserPrincipal;
import ru.bangerok.enterprise.ninja.service.AuthService;
import ru.bangerok.enterprise.ninja.service.MailService;
import ru.bangerok.enterprise.ninja.service.MessageService;

/**
 * Controller for receiving requests from the client related to user authorization/authentication.
 *
 * @author Vladislav [Bangerok] Kuznetsov.
 * @since 0.1.8
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("auth")
public class AuthController {

  private final AuthService authService;
  private final MessageService msgService;
  private final MailService mailService;
  private final ApplicationEventPublisher eventPublisher;

  /**
   * Rest request method called from the client to retrieve information about the authorized user
   * from the database. Before sending a response - the user's rights to access the User entity
   * are checked.
   *
   * @param userPrincipal entity stored in authentication.
   * @return {@link User} or null.
   */
  @PostAuthorize("hasPermission(returnObject, 'READ')")
  @GetMapping("/user")
  @JsonView(Views.UserShortData.class)
  public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
    if (Objects.isNull(userPrincipal)) {
      return null;
    }

    return userPrincipal.getUser();
  }

  /**
   * Rest request method called from the client to authenticate the user by the received data.
   *
   * @param loginReq data required for user authentication.
   * @return {@link ApiResponse} with authentication token.
   */
  @PatchMapping("/login")
  public ApiResponse authenticateUser(@RequestBody LoginDto loginReq) {
    var token = authService.creatingTokenForAuthUser(loginReq.email(), loginReq.password());
    return new ApiResponse(token);
  }

  /**
   * Rest request method called from the client to register a user and save it to the database.
   *
   * @param regReq data required for user registration.
   * @return {@link ApiResponse} with information about successful registration.
   */
  @PostMapping("/register")
  public ApiResponse registerUser(@Valid @RequestBody RegisterDto regReq) {
    var registered = authService.registerNewUserAccount(
        regReq.email(), regReq.name(), regReq.password()
    );
    eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, ""));

    return new ApiResponse(msgService.getMessage(
        "registration.completed.successfully"
    ));
  }

  /**
   * Rest request method called to verify the email of the registered user.
   *
   * @param token user verification token.
   * @return {@link ApiResponse} with information about verification.
   */
  @PutMapping("/registrationConfirm")
  public ApiResponse confirmRegistration(@RequestParam("token") String token) {
    var verificationToken = authService.getVerificationToken(token);

    if (verificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
      return new ApiResponse((Object) verificationToken.getValue());
    }

    var user = verificationToken.getUser();
    user.setEmailVerified(true);
    authService.saveRegisteredUser(user);
    return new ApiResponse(msgService.getMessage(
        "registration.confirmation.successfully"
    ));
  }

  /**
   * Rest request method that sends a new verification token to the user's email.
   *
   * @param existingToken expired user verification token.
   * @return {@link ApiResponse} with information about sending a new token to the user's email.
   */
  @PutMapping("/resendRegistrationToken")
  public ApiResponse resendRegistrationToken(@RequestParam("oldToken") String existingToken) {
    var newToken = authService
        .generateNewVerificationToken(existingToken);
    var user = newToken.getUser();
    mailService.sendVerifiedMessage(user.getEmail(), newToken.getValue());
    return new ApiResponse(msgService.getMessage(
        "registration.confirmation.getting.new.token"
    ));
  }
}