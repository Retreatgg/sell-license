package com.axelor.apps.selllicenseplates2.exception.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorResponse {

    private int status;
    private String message;
    private LocalDateTime timestamp;

}
