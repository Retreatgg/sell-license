package com.axelor.apps.selllicenseplates2.exception.handler;

import com.axelor.apps.selllicenseplates2.exception.CarNumberLotNotFoundException;
import com.axelor.apps.selllicenseplates2.exception.RegionNotFoundException;
import com.axelor.apps.selllicenseplates2.exception.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CarNumberLotNotFoundException.class)
    public ResponseEntity<?> handleCarNumberLotNotFound(CarNumberLotNotFoundException exception) {
        return ResponseEntity.status(NOT_FOUND)
                .body(ErrorResponse.builder()
                        .message(exception.getMessage())
                        .status(NOT_FOUND.value())
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(RegionNotFoundException.class)
    public ResponseEntity<?> handleRegionNotFound(RegionNotFoundException exception) {
        return ResponseEntity.status(NOT_FOUND)
                .body(ErrorResponse.builder()
                        .message(exception.getMessage())
                        .status(NOT_FOUND.value())
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgument(IllegalArgumentException exception) {
        return ResponseEntity.badRequest()
                .body(ErrorResponse.builder()
                        .message(exception.getMessage())
                        .status(400)
                        .timestamp(LocalDateTime.now())
                        .build());
    }
}
