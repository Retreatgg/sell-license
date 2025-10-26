package com.axelor.apps.selllicenseplates2.dto.admin;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CarNumberLotUpdateAdminRequest {
    private String firstLetter;
    private String secondLetter;
    private String thirdLetter;
    private String firstDigit;
    private String secondDigit;
    private String thirdDigit;

    private Long regionId;

    private BigDecimal markupPrice;

    private String comment;

}
