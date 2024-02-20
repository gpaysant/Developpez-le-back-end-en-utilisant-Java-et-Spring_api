package com.openclassrooms.api.controllers;

import com.openclassrooms.api.dto.UserDto;
import com.openclassrooms.api.exceptions.UnauthorizedException;
import com.openclassrooms.api.responses.MyResponseExceptionObject;
import com.openclassrooms.api.responses.MyResponseObject;
import com.openclassrooms.api.services.UserService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;

import static com.openclassrooms.api.validators.ValidationGroups.Authenticate;
import static com.openclassrooms.api.validators.ValidationGroups.Create;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MyResponseObject.class))),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/auth/register")
    public ResponseEntity<?> setUser(@Validated(Create.class) @RequestBody UserDto userDto, BindingResult bindingResult) {
        boolean isError = bindingResult.hasErrors();
        String token = null;
        if (isError || (token = userService.createNewUser(userDto)) == null) {
            return new ResponseEntity<>(new HashMap<>(), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(new MyResponseObject(token));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MyResponseObject.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MyResponseExceptionObject.class)))
    })
    @PostMapping("/auth/login")
    public ResponseEntity<?> authenticateUser(@Validated(Authenticate.class) @RequestBody UserDto userDto, BindingResult bindingResult) {
        boolean isError = bindingResult.hasErrors();
        String token = null;
        if (isError || (token = userService.authenticateUser(userDto)) == null) {
            return new ResponseEntity<>(new MyResponseExceptionObject("error"), HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok(new MyResponseObject(token));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/auth/me")
    public ResponseEntity<?> authenticateUserWithToken(Principal authentication) {
        try {
            UserDto userDto = userService.getUserWithEmail(authentication.getName());
            return ResponseEntity.ok(userDto);
        } catch (UnauthorizedException ex) {
            return new ResponseEntity<>(new HashMap<>(), HttpStatus.UNAUTHORIZED);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserInformation(@PathVariable("id") final int id) {
        try {
            UserDto userDto = userService.getUser(id);
            return ResponseEntity.ok(userDto);
        } catch (UnauthorizedException ex) {
            return new ResponseEntity<>(new HashMap<>(), HttpStatus.UNAUTHORIZED);
        }
    }

}
