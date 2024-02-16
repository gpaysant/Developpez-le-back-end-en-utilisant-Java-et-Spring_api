package com.openclassrooms.api.services;

import com.openclassrooms.api.dto.UserDto;
import com.openclassrooms.api.exceptions.UnauthorizedException;
import com.openclassrooms.api.models.User;

import java.util.Optional;

public interface UserService {

    Optional<User> saveUser(UserDto userDto);

    String createNewUser(UserDto userDto);

    String authenticateUser(UserDto userDto);

    UserDto getUser(int id) throws UnauthorizedException;

    UserDto getUserWithEmail(String email) throws UnauthorizedException;
}
