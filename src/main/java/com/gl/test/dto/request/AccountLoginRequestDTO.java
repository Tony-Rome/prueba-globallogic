package com.gl.test.dto.request;

import lombok.Data;

@Data
public class AccountLoginRequestDTO {
    private String email;
    private String password;
}
