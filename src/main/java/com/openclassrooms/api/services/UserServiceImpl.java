package com.openclassrooms.api.services;

import com.openclassrooms.api.dto.UserDto;
import com.openclassrooms.api.exceptions.UnauthorizedException;
import com.openclassrooms.api.models.User;
import com.openclassrooms.api.repository.UserRepository;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

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
    public Optional<User> saveUser(UserDto userDto) {
        userDto.setUpdateDate(Date.from(Instant.now()));
        String passwordEncoded = this.bCryptPasswordEncoder.encode(userDto.getPassword());
        User user =  this.modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoded);
        try {
            return Optional.of(userRepository.save(user));
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    @Override
    public String createNewUser(UserDto userDto) {
        userDto.setCreateDate(Date.from(Instant.now()));
        Optional<User> userSaved = saveUser(userDto);
        return userSaved.map(user -> jwtService.generateToken(user.getEmail())).orElse(null);
    }

    @Override
    public String authenticateUser(UserDto userDto) {
        String passwordNotEncoded = userDto.getPassword();
        Optional<User> user = userRepository.findByEmail(userDto.getEmail());
        if (user.isEmpty() || !this.bCryptPasswordEncoder.matches(passwordNotEncoded, user.get().getPassword())) {
            return null;
        }
        return jwtService.generateToken(user.get().getEmail());
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