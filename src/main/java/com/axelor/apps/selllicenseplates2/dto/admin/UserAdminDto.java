package com.axelor.apps.selllicenseplates2.dto.admin;

import com.axelor.apps.selllicenseplates2.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAdminDto {

    private Long id;
    private String fullName;
    private String originalPhoneNumber;
    private String changedPhoneNumber;
    private String email;
    private List<Role> roles;

}
