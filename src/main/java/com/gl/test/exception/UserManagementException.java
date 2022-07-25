package com.gl.test.exception;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserManagementException extends RuntimeException{

    private int code;
    private String detail;
    private HttpStatus httpStatus;

    public UserManagementException(HttpStatus httpStatus, String detail){
        super(detail);
        this.code = httpStatus.value();
        this.detail = detail;
        this.httpStatus = httpStatus;
    }
}
