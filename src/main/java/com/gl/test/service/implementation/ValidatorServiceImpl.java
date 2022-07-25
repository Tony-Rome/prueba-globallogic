package com.gl.test.service.implementation;

import com.gl.test.dto.generic.PhoneDTO;
import com.gl.test.dto.request.AccountRequestDTO;
import com.gl.test.exception.UserManagementException;
import com.gl.test.service.ValidatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.gl.test.utils.ErrorMessageUtil.*;

@Slf4j
@Service
public class ValidatorServiceImpl implements ValidatorService {

    private final static String EMAIL_REGEX = "[a-zA-Z]{2,}@[a-zA-Z]{2,}\\.[a-zA-Z]{2,5}";
    private final static String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*\\d.*\\d)(?!(.*[A-Z]){2,}|(.*[0-9]){3,})(?!.*[^\\w\\d]).{8,13}$";

    @Override
    public void validateData(AccountRequestDTO createAccountDTO) {
        log.info("Data validation");
        validateEmail(createAccountDTO.getEmail());
        validatePassword(createAccountDTO.getPassword());
        List<PhoneDTO> phones = createAccountDTO.getPhones();
        if(phones != null) validatePhones(phones);
    }

    @Override
    public void validateEmail(String email){
        boolean isValid = email.matches(EMAIL_REGEX);
        if(!isValid) throw new UserManagementException(HttpStatus.BAD_REQUEST, EMAIL_ERR_DESC);
    }

    @Override
    public void validatePassword(String password){
        boolean isValid = password.matches(PASSWORD_REGEX);
        if(!isValid) throw new UserManagementException(HttpStatus.BAD_REQUEST, PASSWORD_ERR_DESC);
    }

    private void validatePhones(List<PhoneDTO> phonesDTO){
        phonesDTO
                .forEach(phone -> {
                    validatePhoneNumber(phone.getNumber());
                    validateCityCode(phone.getCitycode());
                    validateCountryCode(phone.getCountrycode());
        });
    }

    private void validatePhoneNumber(Integer phoneNumber){
        if(!phoneNumber.toString().matches("\\d{8}"))
            throw new UserManagementException(HttpStatus.BAD_REQUEST, PHONE_ERR_DESC);
    }

    private void validateCityCode(Integer cityCode){
        if(!cityCode.toString().matches("\\d{2}"))
            throw new UserManagementException(HttpStatus.BAD_REQUEST, CITY_CODE_ERR_DESC);
    }

    private void validateCountryCode(String countryCode){
        if(!countryCode.matches("[a-zA-Z]]{2,3}"))
            throw new UserManagementException(HttpStatus.BAD_REQUEST, COUNTRY_CODE_ERR_DESC);
    }
}
