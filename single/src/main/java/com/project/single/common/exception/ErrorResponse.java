package com.project.single.common.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorResponse {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int status;
    private final String error;
    private final String code;
    private final String message;
    private final Object args;

    public static ResponseEntity<ErrorResponse> toResponseEntity(HttpStatus httpStatus, String msg) {
        return ResponseEntity.status(httpStatus).body(ErrorResponse.builder().status(httpStatus.value()).error(httpStatus.name()).message(msg).build());
    }

    public static ResponseEntity<?> toResponseEntity(HttpStatus httpStatus, String msg, Object args) {
        return ResponseEntity.status(httpStatus)
                .body(ErrorResponse.builder().status(httpStatus.value()).error(httpStatus.name()).message(msg).args(args).build());
    }
}
