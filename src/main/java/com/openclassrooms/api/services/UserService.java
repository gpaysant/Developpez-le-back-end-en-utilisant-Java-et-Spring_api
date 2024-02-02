package com.openclassrooms.api.services;


import com.openclassrooms.api.dto.UserDto;
import com.openclassrooms.api.models.User;

import java.text.ParseException;

public interface UserService {

    User saveUser(UserDto userDto) throws ParseException;

    String createNewUser(UserDto userDto) throws ParseException;
}
