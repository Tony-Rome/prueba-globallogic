package com.gl.test.service;

public interface JwtService {

    String generateJwt(String email);
    String getEmailFromJwt(String jwt);
}
