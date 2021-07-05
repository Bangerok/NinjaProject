package ru.bangerok.enterprise.ninja.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * <p> Enumeration class for user roles names. </p>
 *
 * @author Vladislav [Bangerok] Kuznetsov.
 * @since 0.5.7
 */
@Getter
@ToString(doNotUseGetters = true)
@RequiredArgsConstructor
public enum Roles {
  /**
   * The role is the user. Given upon registration.
   */
  ROLE_USER("ROLE_USER"),

  /**
   * Role - administrator.
   */
  ROLE_ADMIN("ROLE_ADMIN");

  /**
   * The role name that is stored in the database.
   */
  private final String name;
}