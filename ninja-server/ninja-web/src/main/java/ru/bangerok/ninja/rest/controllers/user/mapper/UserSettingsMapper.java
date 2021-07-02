package ru.bangerok.ninja.rest.controllers.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.bangerok.ninja.persistence.model.user.UserSetting;
import ru.bangerok.ninja.rest.controllers.user.dto.UserSettingDto;

/**
 * Mapper for an entity representing user preferences for the client interface. To generate DTOs
 * from an entity and back.
 *
 * @author v.kuznetsov
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