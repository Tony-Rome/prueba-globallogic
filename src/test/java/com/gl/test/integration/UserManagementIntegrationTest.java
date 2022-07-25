package com.gl.test.integration;

import com.gl.test.controller.UserManagementController;
import com.gl.test.dao.entity.AccountEntity;
import com.gl.test.dao.repository.AccountRepository;
import com.gl.test.dao.repository.PhoneRepository;
import com.gl.test.dto.request.AccountRequestDTO;
import com.gl.test.dto.response.AccountInfoResponseDTO;
import com.gl.test.dto.response.AccountResponseDTO;
import com.gl.test.exception.UserManagementException;
import com.gl.test.service.implementation.UserDetailsImpl;
import com.gl.test.utils.DatetimeUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;

@SpringBootTest
public class UserManagementIntegrationTest {

    @Autowired
    private UserManagementController userManagementController;

    @Mock
    private Authentication authentication;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void singUpValidUserWithoutPhones(){
        ResponseEntity<AccountResponseDTO> response = userManagementController.signup(getDummyFirstAccountRequest());

        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void signUpUserWithSavedEmail(){
        accountRepository.save(getDummyAccountEntity());
        Throwable exception = Assertions.assertThrows(UserManagementException.class,
                () -> userManagementController.signup(getDummySecondAccountRequest()));
        Assertions.assertEquals("Email ya registrado", exception.getMessage());
    }

    @Test
    void loginUser() {
        SecurityContext securityContext = new SecurityContextImpl();
        securityContext.setAuthentication(new TestingAuthenticationToken(dummyPrincipal(),null));
        SecurityContextHolder.setContext(securityContext);

        accountRepository.save(getDummyAccountEntity());

        ResponseEntity<AccountInfoResponseDTO> response = userManagementController.login();
        System.out.println(response.getBody());
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }

    private AccountRequestDTO getDummyFirstAccountRequest(){
        AccountRequestDTO account = new AccountRequestDTO();
        account.setName("User test");
        account.setEmail("userdummy@test.cl");
        account.setPassword("userP4ssw0rd");
        return account;
    }

    private AccountRequestDTO getDummySecondAccountRequest(){
        AccountRequestDTO account = new AccountRequestDTO();
        account.setName("User test");
        account.setEmail("usertest@test.cl");
        account.setPassword("userP4ssw0rd");
        return account;
    }

    private AccountEntity getDummyAccountEntity(){
        return AccountEntity.builder()
                .uuid("uuidUniqueTest")
                .created(DatetimeUtil.getCurrentDateTime())
                .lastLogin(DatetimeUtil.getCurrentDateTime())
                .email("usertest@test.cl")
                .isActive(true).build();
    }

    private Object dummyPrincipal(){
        return UserDetailsImpl.builder().userName("usertest@test.cl").build();
    }

}
