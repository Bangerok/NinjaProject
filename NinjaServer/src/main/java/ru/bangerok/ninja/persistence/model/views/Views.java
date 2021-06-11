package ru.bangerok.ninja.persistence.model.views;

/**
 * A class with interfaces to markers for entity fields that need to be serialized to be sent from
 * the server.
 *
 * @author v.kuznetsov
 * @since 0.5.9
 */
public class Views {

  public interface BaseId {

  }

  public interface BaseFull extends BaseId {

  }

  public interface UserShortData extends BaseId {

  }

  public interface UserFullData extends UserShortData {

  }

  public interface UserSettingsData extends BaseId {

  }
}
