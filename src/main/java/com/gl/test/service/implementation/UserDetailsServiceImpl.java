package com.gl.test.service.implementation;

import com.gl.test.dao.repository.AccountRepository;
import com.gl.test.exception.UserManagementException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.gl.test.utils.ErrorMessageUtil.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        String accountEmail = Optional.ofNullable(accountRepository.verifyAccountEmail(email))
                .orElseThrow(() -> new UserManagementException(HttpStatus.BAD_REQUEST, USER_NOT_FOUND));
        return UserDetailsImpl.builder()
                .userName(accountEmail)
                .build();
    }
}