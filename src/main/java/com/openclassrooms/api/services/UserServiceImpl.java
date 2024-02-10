package com.openclassrooms.api.services;

import com.openclassrooms.api.dto.UserDto;
import com.openclassrooms.api.exceptions.UnauthorizedException;
import com.openclassrooms.api.models.User;
import com.openclassrooms.api.repository.UserRepository;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.util.Date;

@Data
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, JWTService jwtService, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User saveUser(UserDto userDto) throws ParseException {
        userDto.setUpdateDate(Date.from(Instant.now()));
        String passwordEncoded = this.bCryptPasswordEncoder.encode(userDto.getPassword());
        User user =  this.modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoded);
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @Override
    public String createNewUser(UserDto userDto) throws ParseException {
        userDto.setCreateDate(Date.from(Instant.now()));
        User userSaved = saveUser(userDto);
        return jwtService.generateToken(userSaved.getEmail());
    }

    @Override
    public String authenticateUser(UserDto userDto) throws UnauthorizedException {
        String passwordNotEncoded = userDto.getPassword();
        User user = userRepository.findByEmail(userDto.getEmail())
                .orElseThrow(UnauthorizedException::new);
        if (this.bCryptPasswordEncoder.matches(passwordNotEncoded, user.getPassword())) {
            return jwtService.generateToken(user.getEmail());
        }
        return null;
    }

    @Override
    public UserDto getUser(int id) throws UnauthorizedException {
        return userRepository.findById((long)id)
                .map(user -> this.modelMapper.map(user, UserDto.class))
                .orElseThrow(UnauthorizedException::new);
    }

    @Override
    public UserDto getUserWithEmail(String email) throws UnauthorizedException {
        return userRepository.findByEmail(email)
                .map(user -> this.modelMapper.map(user, UserDto.class))
                .orElseThrow(UnauthorizedException::new);
    }

}