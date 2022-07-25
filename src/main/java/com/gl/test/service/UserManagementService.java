package com.gl.test.service;

import com.gl.test.dto.request.AccountRequestDTO;
import com.gl.test.dto.response.AccountInfoResponseDTO;
import com.gl.test.dto.response.AccountResponseDTO;

public interface UserManagementService {

    AccountResponseDTO signup(AccountRequestDTO createAccountDTO);
    AccountInfoResponseDTO login();
}
