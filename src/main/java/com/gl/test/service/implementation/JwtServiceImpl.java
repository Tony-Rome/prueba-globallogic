package com.gl.test.service.implementation;

import com.gl.test.exception.UserManagementException;
import com.gl.test.service.JwtService;
import com.gl.test.utils.DatetimeUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

import static com.gl.test.utils.ErrorMessageUtil.JWT_ERR_DESC;

@Service
public class JwtServiceImpl implements JwtService {

    private static String SECRET = "test-jwt-secret";
    private static int EXPIRATION = 3600;

    @Override
    public String generateJwt(String email){

        LocalDateTime currentDatetime = DatetimeUtil.getCurrentDateTime();
        Date issuedAt = DatetimeUtil.toDate(currentDatetime);
        Date expiration = DatetimeUtil.toDate(currentDatetime.plusSeconds(EXPIRATION));
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    @Override
    public String getEmailFromJwt(String jwt){
        try{
            Jws<Claims> claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(jwt);
            return claims.getBody().getSubject();
        }
        catch (Exception ex){
            throw new UserManagementException(HttpStatus.BAD_REQUEST, JWT_ERR_DESC);
        }
    }
}
