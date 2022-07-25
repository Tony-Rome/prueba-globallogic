package com.gl.test.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorMessage {

    private LocalDateTime timestamp;
    private int code;
    private String detail;
}
