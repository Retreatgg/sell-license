package com.axelor.apps.selllicenseplates2.mapper;

import com.axelor.apps.selllicenseplates2.dto.RoleDto;
import com.axelor.apps.selllicenseplates2.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleMapper {

    @Mapping(target = "roleName", source = "role")
    RoleDto toDto(Role role);
}
