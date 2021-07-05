package ru.bangerok.enterprise.ninja.persistence.model.base;

/**
 * <p> Enumeration class for project entity statuses. </p>
 *
 * @author Vladislav [Bangerok] Kuznetsov.
 * @since 0.3.3
 */
public enum BaseStatus {
  /**
   * Status indicating the activity of a record in the database.
   */
  ACTIVE,

  /**
   * Status indicating inactivity of a record in the database.
   */
  DELETED
}