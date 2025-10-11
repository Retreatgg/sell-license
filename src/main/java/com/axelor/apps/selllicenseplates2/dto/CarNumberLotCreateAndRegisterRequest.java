package com.axelor.apps.selllicenseplates2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CarNumberLotCreateAndRegisterRequest extends CarNumberLotCreateRequest {

    private String fullName;
    private String email;
    private String phoneNumber;
    private String password;

}
