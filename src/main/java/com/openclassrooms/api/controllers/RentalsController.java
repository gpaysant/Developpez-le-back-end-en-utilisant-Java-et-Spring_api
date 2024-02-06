package com.openclassrooms.api.controllers;

import com.openclassrooms.api.dto.RentalDto;
import com.openclassrooms.api.dto.UserDto;
import com.openclassrooms.api.responses.MyResponseMessageObject;
import com.openclassrooms.api.services.RentalService;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
public class RentalsController {

    @Autowired
    RentalService rentalService;

    @Autowired
    private UserService userService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MyResponseMessageObject.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PostMapping("/rentals")
    public ResponseEntity<?> setRental(@RequestHeader("Authorization") String bearerToken, @Valid @RequestBody RentalDto rentalDto) throws ParseException {
        UserDto userDto = userService.getUserFromToken(bearerToken);
        rentalDto.setUserDto(userDto);
        rentalService.createNewRental(rentalDto);
        MyResponseMessageObject responseObject = new MyResponseMessageObject();
        responseObject.setMessage("Rental Created");
        return ResponseEntity.ok(responseObject);
    }
}
