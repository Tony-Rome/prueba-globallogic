package com.gl.test.controller;

import com.gl.test.dto.request.AccountRequestDTO;
import com.gl.test.dto.response.AccountInfoResponseDTO;
import com.gl.test.dto.response.AccountResponseDTO;
import org.springframework.http.ResponseEntity;

public interface UserManagementController {

    ResponseEntity<AccountResponseDTO> singup(AccountRequestDTO createAccountDTO);
    ResponseEntity<AccountInfoResponseDTO> login();
}
