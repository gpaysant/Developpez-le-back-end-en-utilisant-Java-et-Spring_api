package com.openclassrooms.api.services;

import com.openclassrooms.api.dto.UserDto;
import com.openclassrooms.api.models.User;
import com.openclassrooms.api.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

@Data
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, JWTService jwtService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.modelMapper = modelMapper;
    }

    @Override
    public User saveUser(UserDto userDto) throws ParseException {
        userDto.setUpdated_at(Date.from(Instant.now()));
        User user = convertToEntity(userDto);
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @Override
    public String createNewUser(UserDto userDto) throws ParseException {
        userDto.setCreated_at(Date.from(Instant.now()));
        User userSaved = saveUser(userDto);
        String token = jwtService.generateToken(userSaved.getEmail());
        return token;
    }

    private User convertToEntity(UserDto userDto) throws ParseException {
        User user = this.modelMapper.map(userDto, User.class);
        return user;
    }
}