package com.openclassrooms.api.controllers;

import com.openclassrooms.api.dto.UserDto;
import com.openclassrooms.api.responses.MyResponseExceptionObject;
import com.openclassrooms.api.responses.MyResponseObject;
import com.openclassrooms.api.services.UserService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

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
    public ResponseEntity<?> setUser(@Validated(Create.class) @RequestBody UserDto userDto) throws ParseException {
        String token = userService.createNewUser(userDto);
        MyResponseObject responseObject = new MyResponseObject();
        responseObject.setToken(token);
        return ResponseEntity.ok(responseObject);
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
    public ResponseEntity<?> authenticateUser(@Validated(Authenticate.class) @RequestBody UserDto userDto) {
        String token = userService.authenticateUser(userDto);
        MyResponseObject responseObject = new MyResponseObject();
        responseObject.setToken(token);
        return ResponseEntity.ok(responseObject);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/auth/me")
    public ResponseEntity<?> authenticateUserWithToken(@RequestHeader("Authorization") String bearerToken) {
        UserDto userDto = userService.getUserFromToken(bearerToken);
        return ResponseEntity.ok(userDto);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserInformation(@PathVariable("id") final int id) {
        UserDto userDto = userService.getUser(id);
        return ResponseEntity.ok(userDto);
    }

}
