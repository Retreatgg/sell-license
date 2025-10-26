package com.axelor.apps.selllicenseplates2.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarNumberLotAdminDto {

    private Long id;
    private String fullCarNumber;

    private String firstLetter;
    private String secondLetter;
    private String thirdLetter;
    private String firstDigit;
    private String secondDigit;
    private String thirdDigit;

    private String regionCode;
    private BigDecimal originalPrice;
    private BigDecimal markupPrice;
    private String phoneNumber;
    private String fullName;
    private Instant createdDate;
    private Instant updatedDate;
    private String comment;
    private Boolean isConfirm;

    private UserAdminDto author;
}
