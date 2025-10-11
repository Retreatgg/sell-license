package com.axelor.apps.selllicenseplates2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedBackCreateRequest {

    private String fullName;
    private String phoneNumber;
    private String carNumber;
    private String feedbackType;

}
