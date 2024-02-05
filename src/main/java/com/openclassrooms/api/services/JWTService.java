package com.openclassrooms.api.services;

public interface JWTService {


    String generateToken(String email);

    String getSubject(String token);
}
