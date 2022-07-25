package com.gl.test.service.implementation;

import com.gl.test.dao.entity.AccountEntity;
import com.gl.test.dao.entity.PhoneEntity;
import com.gl.test.dao.repository.AccountRepository;
import com.gl.test.dao.repository.PhoneRepository;
import com.gl.test.dto.generic.PhoneDTO;
import com.gl.test.dto.request.AccountLoginRequestDTO;
import com.gl.test.dto.request.AccountRequestDTO;
import com.gl.test.dto.response.AccountInfoResponseDTO;
import com.gl.test.dto.response.AccountResponseDTO;
import com.gl.test.exception.UserManagementException;
import com.gl.test.mapper.AccountMapper;
import com.gl.test.mapper.PhoneMapper;
import com.gl.test.service.UserManagementService;
import com.gl.test.service.ValidatorService;
import com.gl.test.utils.DatetimeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.gl.test.utils.ErrorMessageUtil.BAD_CREDENTIALS_ERR_DESC;
import static com.gl.test.utils.ErrorMessageUtil.EMAIL_EXISTS_ERR_DESC;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserManagementServiceImpl implements UserManagementService {

    private final ValidatorService validatorService;
    private final AccountRepository accountRepository;
    private final PhoneRepository phoneRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtServiceImpl jwtService;

    @Override
    @Transactional
    public AccountResponseDTO signup(AccountRequestDTO accountRequestDTO) {
        log.info("signup");

        validatorService.validateData(accountRequestDTO);

        String email = accountRepository.verifyAccountEmail(accountRequestDTO.getEmail());
        if(email != null) throw new UserManagementException(HttpStatus.BAD_REQUEST, EMAIL_EXISTS_ERR_DESC);

        String password = passwordEncoder.encode(accountRequestDTO.getPassword());
        AccountEntity accountEntity = AccountMapper.toAccountEntity(accountRequestDTO, password);
        accountRepository.save(accountEntity);

        List<PhoneDTO> phonesRequest = accountRequestDTO.getPhones();
        if(phonesRequest != null && !phonesRequest.isEmpty()){
            List<PhoneEntity> phones = PhoneMapper.toPhoneEntity(phonesRequest, accountEntity);
            phoneRepository.saveAll(phones);
        }

        return getAccountResponse(accountEntity);
    }

    @Override
    public AccountInfoResponseDTO login(AccountLoginRequestDTO accountLoginRequestDTO) {
        log.info("login");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = ((UserDetailsImpl) principal).getUsername();
        AccountEntity account = accountRepository.findByEmail(email);
        isCredentialsValid(accountLoginRequestDTO, account.getEmail(), account.getPassword());
        AccountInfoResponseDTO accountInfoResponseDTO = getAccountInfoResponse(account);
        account.setLastLogin(DatetimeUtil.getCurrentDateTime());
        accountRepository.save(account);
        return accountInfoResponseDTO;

    }

    private void isCredentialsValid(AccountLoginRequestDTO accountLoginRequestDTO, String email, String passpord){
        if(!accountLoginRequestDTO.getEmail().equals(email) ||
                !passwordEncoder.matches(accountLoginRequestDTO.getPassword(), passpord))
            throw new UserManagementException(HttpStatus.BAD_REQUEST, BAD_CREDENTIALS_ERR_DESC);
    }

    private AccountResponseDTO getAccountResponse(AccountEntity accountEntity){
        return AccountResponseDTO
                .builder()
                .id(accountEntity.getUuid())
                .created(accountEntity.getCreated())
                .lastLogin(accountEntity.getLastLogin())
                .token(jwtService.generateJwt(accountEntity.getEmail()))
                .isActive(accountEntity.isActive())
                .build();

    }

    private AccountInfoResponseDTO getAccountInfoResponse(AccountEntity accountEntity){
        return AccountInfoResponseDTO.builder()
                .id(accountEntity.getUuid())
                .created(accountEntity.getCreated())
                .lastLogin(accountEntity.getLastLogin())
                .token(jwtService.generateJwt(accountEntity.getEmail()))
                .isActive(accountEntity.isActive())
                .name(accountEntity.getName())
                .email(accountEntity.getEmail())
                .password(accountEntity.getPassword())
                .phones(PhoneMapper.toPhoneDTO(accountEntity.getPhones())).build();
    }
}
