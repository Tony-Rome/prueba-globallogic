package com.gl.test.mapper;

import com.gl.test.dao.entity.AccountEntity;
import com.gl.test.dto.request.AccountRequestDTO;
import com.gl.test.utils.DatetimeUtil;

import java.util.UUID;

public class AccountMapper {

    public static AccountEntity toAccountEntity(AccountRequestDTO accountRequestDTO, String password){
        return AccountEntity.builder()
                .uuid(UUID.randomUUID().toString())
                .name(accountRequestDTO.getName().toLowerCase())
                .email(accountRequestDTO.getEmail().toLowerCase())
                .password(password)
                .lastLogin(DatetimeUtil.getCurrentDateTime())
                .created(DatetimeUtil.getCurrentDateTime())
                .isActive(true)
                .build();

    }
}
