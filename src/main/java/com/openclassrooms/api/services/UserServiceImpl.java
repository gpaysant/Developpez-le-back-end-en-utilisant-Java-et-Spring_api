package com.openclassrooms.api.services;

import com.openclassrooms.api.dto.UserDto;
import com.openclassrooms.api.exceptions.UnauthorizedException;
import com.openclassrooms.api.models.User;
import com.openclassrooms.api.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, JWTService jwtService, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User saveUser(UserDto userDto) throws ParseException {
        userDto.setUpdated_at(Date.from(Instant.now()));
        String passwordEncoded = this.bCryptPasswordEncoder.encode(userDto.getPassword());
        User user = convertToEntity(userDto);
        user.setPassword(passwordEncoded);
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

    @Override
    public String authenticateUser(UserDto userDto) throws UnauthorizedException {
        String passwordNotEncoded = userDto.getPassword();
        User user = userRepository.findByEmail(userDto.getEmail());
        if (user != null) {
            if (this.bCryptPasswordEncoder.matches(passwordNotEncoded, user.getPassword())) {
                String token = jwtService.generateToken(user.getEmail());
                return token;
            }
            throw new UnauthorizedException("Login or/and password are not correct");
        }
        throw new UnauthorizedException("this user doesn't exist");
    }

    private User convertToEntity(UserDto userDto) throws ParseException {
        User user = this.modelMapper.map(userDto, User.class);
        return user;
    }
}