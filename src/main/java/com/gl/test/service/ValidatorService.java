package com.gl.test.service;

import com.gl.test.dto.request.AccountRequestDTO;

public interface ValidatorService {

    void validateData(AccountRequestDTO createAccountDTO);
    void validateEmail(String email);
    void validatePassword(String password);
}
