package ru.bangerok.enterprise.ninja.rest.controllers.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.bangerok.enterprise.ninja.persistence.model.user.UserSetting;
import ru.bangerok.enterprise.ninja.rest.controllers.user.dto.UserSettingDto;

/**
 * Mapper for an entity representing user preferences for the client interface. To generate DTOs
 * from an entity and back.
 *
 * @author Vladislav [Bangerok] Kuznetsov.
 * @since 0.6.3
 */
@Mapper
public interface UserSettingsMapper {

  /**
   * Getting an entity from dto.
   *
   * @param dto dto user setting.
   * @return user setting.
   */
  @Mapping(target = "user", ignore = true)
  @Mapping(target = "status", ignore = true)
  @Mapping(target = "created", ignore = true)
  @Mapping(target = "updated", ignore = true)
  UserSetting toEntity(UserSettingDto dto);
}