package com.gl.test.exception;

import com.gl.test.utils.DatetimeUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.gl.test.utils.ErrorMessageUtil.GLOBAL_ERR_DESC;
import static com.gl.test.utils.ErrorMessageUtil.NULL_VALUE_ERR_DESC;

@ControllerAdvice
public class UserManagementHandlerAdvice {

    @ExceptionHandler( value = {Exception.class})
    public ResponseEntity<ErrorMessage> handleException(Exception ex){
        return new ResponseEntity<>(
                getErrorDTO(HttpStatus.BAD_REQUEST.value(), GLOBAL_ERR_DESC),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {UserManagementException.class})
    public ResponseEntity<ErrorMessage> handleUserManagementException(UserManagementException ex){
        return new ResponseEntity<>(getErrorDTO(ex.getCode(), ex.getMessage()), ex.getHttpStatus());
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorMessage> handleNullPointerException(NullPointerException ex){
        return new ResponseEntity<>(
                getErrorDTO(HttpStatus.BAD_REQUEST.value(), NULL_VALUE_ERR_DESC),
                HttpStatus.BAD_REQUEST);
    }

    private ErrorMessage getErrorDTO(int code, String message){
        return ErrorMessage.builder()
                .timestamp(DatetimeUtil.getCurrentDateTime())
                .code(code)
                .detail(message)
                .build();
    }
}
