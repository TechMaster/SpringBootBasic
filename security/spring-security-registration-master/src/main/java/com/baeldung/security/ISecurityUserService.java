package com.baeldung.security;

public interface ISecurityUserService {

    String validatePasswordResetToken(String token);

}
