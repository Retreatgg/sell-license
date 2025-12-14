package com.axelor.apps.selllicenseplates2.mapper;

import com.axelor.apps.selllicenseplates2.dto.UserDto;
import com.axelor.apps.selllicenseplates2.dto.admin.UserAdminDto;
import com.axelor.apps.selllicenseplates2.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-14T16:49:24+0600",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.9 (Microsoft)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setFullName( user.getFullName() );
        userDto.setEmail( user.getEmail() );

        return userDto;
    }

    @Override
    public List<UserDto> toDtoList(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( users.size() );
        for ( User user : users ) {
            list.add( toDto( user ) );
        }

        return list;
    }

    @Override
    public UserAdminDto toAdminDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserAdminDto userAdminDto = new UserAdminDto();

        userAdminDto.setId( user.getId() );
        userAdminDto.setFullName( user.getFullName() );
        userAdminDto.setOriginalPhoneNumber( user.getOriginalPhoneNumber() );
        userAdminDto.setChangedPhoneNumber( user.getChangedPhoneNumber() );
        userAdminDto.setEmail( user.getEmail() );

        return userAdminDto;
    }

    @Override
    public List<UserAdminDto> toAdminDtoList(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserAdminDto> list = new ArrayList<UserAdminDto>( users.size() );
        for ( User user : users ) {
            list.add( toAdminDto( user ) );
        }

        return list;
    }
}
