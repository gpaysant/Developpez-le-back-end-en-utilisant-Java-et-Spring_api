package com.openclassrooms.api.controllers;

import com.openclassrooms.api.dto.UserDto;
import com.openclassrooms.api.responses.MyResponseObject;
import com.openclassrooms.api.services.UserService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

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
    public ResponseEntity<?> setUser(@Valid @RequestBody UserDto userDto) throws ParseException { //, BindingResult bindingResult) throws ParseException {

        String token = userService.createNewUser(userDto);
        MyResponseObject responseObject = new MyResponseObject();
        responseObject.setToken(token);
        return ResponseEntity.ok(responseObject);
    }

}
