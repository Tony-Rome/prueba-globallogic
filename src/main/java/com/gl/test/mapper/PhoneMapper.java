package com.gl.test.mapper;

import com.gl.test.dao.entity.AccountEntity;
import com.gl.test.dao.entity.PhoneEntity;
import com.gl.test.dto.generic.PhoneDTO;

import java.util.List;
import java.util.stream.Collectors;

public class PhoneMapper {

    public static List<PhoneDTO> toPhoneDTO(List<PhoneEntity> phoneEntities){
        if(phoneEntities == null || phoneEntities.isEmpty()) return null;
        return phoneEntities
                .stream()
                .map(phone -> PhoneDTO.builder()
                        .number(phone.getNumber())
                        .citycode(phone.getCityCode())
                        .countrycode(phone.getCountryCode())
                        .build()).collect(Collectors.toList());
    }

    public static List<PhoneEntity> toPhoneEntity(List<PhoneDTO> phoneRequestDTO, AccountEntity account){
        return phoneRequestDTO
                .stream()
                .map(phone -> PhoneEntity.builder()
                        .number(phone.getNumber())
                        .cityCode(phone.getCitycode())
                        .countryCode(phone.getCountrycode().toUpperCase())
                        .account(account)
                        .build())
                .collect(Collectors.toList());
    }
}
