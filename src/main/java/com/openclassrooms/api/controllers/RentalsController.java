package com.openclassrooms.api.controllers;

import com.openclassrooms.api.dto.RentalDto;
import com.openclassrooms.api.dto.UserDto;
import com.openclassrooms.api.responses.MyResponseMessageObject;
import com.openclassrooms.api.responses.MyResponseRentalObject;
import com.openclassrooms.api.services.RentalService;
import com.openclassrooms.api.services.UserService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

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

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MyResponseMessageObject.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/rentals")
    public ResponseEntity<?> getRentals() throws ParseException {
        List<RentalDto> rentals = rentalService.getRentals();
        MyResponseRentalObject responseObject = new MyResponseRentalObject();
        responseObject.setRentals(rentals);
        return ResponseEntity.ok(responseObject);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MyResponseMessageObject.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/rentals/{id}")
    public ResponseEntity<?> getRental(@PathVariable("id") final int id) {
        RentalDto rentalDto = rentalService.getRental(id);
        return ResponseEntity.ok(rentalDto);
    }

}
