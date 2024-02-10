package com.openclassrooms.api.controllers;

import com.openclassrooms.api.dto.RentalDto;
import com.openclassrooms.api.exceptions.UnauthorizedException;
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

import java.io.IOException;
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
    public ResponseEntity<?> setRental(@RequestHeader("Authorization") String bearerToken, @ModelAttribute RentalDto rentalDto) throws ParseException, IOException {
        //TODO : httpservletRequest en parametre
        /*UserDto userDto = userService.getUserFromToken(bearerToken);
        rentalDto.setUserDto(userDto);
        rentalService.createNewRental(rentalDto);*/
        return ResponseEntity.ok(new MyResponseMessageObject("Rental Created"));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MyResponseRentalObject.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/rentals")
    public ResponseEntity<?> getRentals() throws ParseException {
        List<RentalDto> rentals = rentalService.getRentals();
        return ResponseEntity.ok(new MyResponseRentalObject(rentals));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MyResponseMessageObject.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/rentals/{id}")
    public ResponseEntity<?> getRental(@PathVariable("id") final int id) throws UnauthorizedException {
        RentalDto rentalDto = rentalService.getRental(id);
        return ResponseEntity.ok(rentalDto);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MyResponseMessageObject.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PutMapping("/rentals/{id}")
    public ResponseEntity<?> updateRental(@PathVariable("id") final int id, @Valid @RequestBody RentalDto rentalDto) throws ParseException {
        rentalDto.setId((long)id);
        rentalService.updateRental(rentalDto);
        return ResponseEntity.ok(new MyResponseMessageObject("Rental updated !"));
    }

}
