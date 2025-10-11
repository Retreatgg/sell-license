package com.axelor.apps.selllicenseplates2.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAdminUpdateDto {

    private String fullName;
    private String originalPhoneNumber;
    private String changedPhoneNumber;
    private Boolean enabled;
    private Long roleId;

}
