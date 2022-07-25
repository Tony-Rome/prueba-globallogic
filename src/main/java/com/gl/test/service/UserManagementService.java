package com.gl.test.service;

import com.gl.test.dto.request.AccountRequestDTO;
import com.gl.test.dto.response.AccountInfoResponseDTO;
import com.gl.test.dto.response.AccountResponseDTO;

public interface UserManagementService {

    AccountResponseDTO singup(AccountRequestDTO createAccountDTO);
    AccountInfoResponseDTO login();
}
