package com.gl.test.controller;

import com.gl.test.dto.request.AccountRequestDTO;
import com.gl.test.dto.response.AccountInfoResponseDTO;
import com.gl.test.dto.response.AccountResponseDTO;
import com.gl.test.service.UserManagementService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/account")
@AllArgsConstructor
public class UserManagementControllerImpl implements UserManagementController{

    private final UserManagementService userManagementService;

    @Override
    @PostMapping(value = "/singup")
    public ResponseEntity<AccountResponseDTO> singup(@RequestBody AccountRequestDTO createAccountDTO) {
        return new ResponseEntity<>(userManagementService.singup(createAccountDTO), HttpStatus.OK);
    }

    @Override
    @GetMapping(value = "/login")
    public ResponseEntity<AccountInfoResponseDTO> login(){
        return new ResponseEntity<>(userManagementService.login(), HttpStatus.OK);
    }
}
