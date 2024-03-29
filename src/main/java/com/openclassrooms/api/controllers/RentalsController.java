package com.openclassrooms.api.controllers;

import com.openclassrooms.api.dto.InputRentalDto;
import com.openclassrooms.api.dto.RentalDto;
import com.openclassrooms.api.dto.UserDto;
import com.openclassrooms.api.exceptions.UnauthorizedException;
import com.openclassrooms.api.responses.MyResponseMessageObject;
import com.openclassrooms.api.responses.MyResponseRentalObject;
import com.openclassrooms.api.services.RentalService;
import com.openclassrooms.api.services.UserService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.util.HashMap;
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
    @PostMapping(value="/rentals", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> setRental(Principal authentication, @ModelAttribute InputRentalDto inputRentalDto, HttpServletRequest httpServletRequest) throws IOException {
        try {
            UserDto userDto = userService.getUserWithEmail(authentication.getName());
            inputRentalDto.setUserDto(userDto);
        } catch (UnauthorizedException ex) {
            return new ResponseEntity<>(new HashMap<>(), HttpStatus.UNAUTHORIZED);
        }
        rentalService.createNewRental(inputRentalDto, httpServletRequest);
        return ResponseEntity.ok(new MyResponseMessageObject("Rental Created"));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RentalDto.class))),
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
                            schema = @Schema(implementation = RentalDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/rentals/{id}")
    public ResponseEntity<?> getRental(@PathVariable("id") final int id) {
        try {
            RentalDto rentalDto = rentalService.getRental(id);
            return ResponseEntity.ok(rentalDto);
        } catch (UnauthorizedException ex) {
            return new ResponseEntity<>(new HashMap<>(), HttpStatus.UNAUTHORIZED);
        }

    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MyResponseMessageObject.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PutMapping(value="/rentals/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateRental(@PathVariable("id") final int id, @Valid @ModelAttribute RentalDto rentalDto) {
        rentalDto.setId((long)id);
        rentalService.updateRental(rentalDto);
        return ResponseEntity.ok(new MyResponseMessageObject("Rental updated !"));
    }

}
