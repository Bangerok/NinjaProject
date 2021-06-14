package ru.bangerok.ninja.persistence.model.views;

/**
 * A class with interfaces to markers for entity fields that need to be serialized to be sent from
 * the server.
 *
 * @author v.kuznetsov
 * @since 0.5.9
 */
public class Views {

  /**
   * A marker interface to include in serialization only the identifier from the underlying entity
   * parameters.
   */
  public interface BaseId {

  }

  /**
   * A marker interface for including all basic entity parameters in serialization.
   */
  public interface BaseFull extends BaseId {

  }

  /**
   * A marker interface for including a minimum number of custom entity parameters in serialization.
   */
  public interface UserShortData extends BaseId {

  }

  /**
   * An interface token to include in serialization the maximum number of custom entity parameters
   * allowed.
   */
  public interface UserFullData extends UserShortData {

  }

  /**
   * Interface marker to include in serialization meaningful entity parameters for custom
   * application interface settings.
   */
  public interface UserSettingsData extends BaseId {

  }
}
