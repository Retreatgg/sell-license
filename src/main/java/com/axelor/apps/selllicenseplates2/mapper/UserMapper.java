package com.axelor.apps.selllicenseplates2.mapper;

import com.axelor.apps.selllicenseplates2.dto.UserDto;
import com.axelor.apps.selllicenseplates2.dto.admin.UserAdminDto;
import com.axelor.apps.selllicenseplates2.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserDto toDto(User user);
    List<UserDto> toDtoList(List<User> users);
    UserAdminDto toAdminDto(User user);
    List<UserAdminDto> toAdminDtoList(List<User> users);

}
