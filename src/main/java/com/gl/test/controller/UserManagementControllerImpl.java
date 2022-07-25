package com.gl.test.controller;

import com.gl.test.dto.request.AccountRequestDTO;
import com.gl.test.dto.request.AccountLoginRequestDTO;
import com.gl.test.dto.response.AccountInfoResponseDTO;
import com.gl.test.dto.response.AccountResponseDTO;
import com.gl.test.service.UserManagementService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/account")
@AllArgsConstructor
public class UserManagementControllerImpl implements UserManagementController{

    private final UserManagementService userManagementService;

    @Override
    @PostMapping(value = "/sign-up")
    public ResponseEntity<AccountResponseDTO> signup(@RequestBody AccountRequestDTO createAccountDTO) {
        return new ResponseEntity<>(userManagementService.signup(createAccountDTO), HttpStatus.CREATED);
    }

    @Override
    @GetMapping(value = "/login")
    public ResponseEntity<AccountInfoResponseDTO> login(@RequestBody AccountLoginRequestDTO accountLoginRequestDTO){
        return new ResponseEntity<>(userManagementService.login(accountLoginRequestDTO), HttpStatus.OK);
    }
}
