package com.gl.test.unit

import com.gl.test.UserManagementApplication
import com.gl.test.dao.repository.AccountRepository
import com.gl.test.dao.repository.PhoneRepository
import com.gl.test.dto.generic.PhoneDTO
import com.gl.test.dto.request.AccountRequestDTO
import com.gl.test.exception.UserManagementException
import com.gl.test.service.JwtService
import com.gl.test.service.ValidatorService
import com.gl.test.service.implementation.JwtServiceImpl
import com.gl.test.service.implementation.ValidatorServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.context.SpringBootContextLoader
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestContext
import org.springframework.test.context.web.WebAppConfiguration
import spock.lang.Specification

class UserManagementTest extends Specification{

    PhoneRepository phoneRepository = Mock()
    AccountRepository accountRepository = Mock()

    final JwtService jwtService = new JwtServiceImpl()
    final ValidatorService validatorService = new ValidatorServiceImpl()

    def "Verify JWT creation"(){
        given:

        String emailIn = "email@test.com";

        when:
        String jwt = jwtService.generateJwt(emailIn)
        String emailOut = jwtService.getEmailFromJwt(jwt)

        then:
        jwt != null
        emailOut != null
    }

    def "Verify good emails"(){
        when:
        validatorService.validateEmail(email)

        then:
        expectedException

        where:
        email         || expectedException
        "emailTest@test.com" || void
        "mail@mail.com"   || void
        "glt@test.io"|| void
    }

    def "Verify bad emails"(){
        when:
        validatorService.validateEmail(email)

        then:
        thrown expectedException

        where:
        email         || expectedException
        "email123@test.com" || UserManagementException
        "mail.com"   || UserManagementException
        "email.test.cl"|| UserManagementException
    }

    def "Verify strong password"(){
        when:
        validatorService.validatePassword(password)

        then:
        expected

        where:
        password      | expected
        "1ksj8dsalkoK"| void
        "kjsKkah2nrw2"| void
        "11ksjdsalkA" | void
    }

    def "Verify weak password"(){

        when:
        validatorService.validatePassword(password)

        then:
        thrown expectedException

        where:
        password      || expectedException
        "1ksj8dsalko" || UserManagementException
        "@213kjsKk"   || UserManagementException
        "11ksj8dsalko"|| UserManagementException
    }

}
