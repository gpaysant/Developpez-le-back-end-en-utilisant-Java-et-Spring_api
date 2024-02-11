package com.openclassrooms.api.services;

import com.openclassrooms.api.dto.InputRentalDto;
import com.openclassrooms.api.dto.RentalDto;
import com.openclassrooms.api.exceptions.UnauthorizedException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface RentalService {

    void createNewRental(InputRentalDto inputRentalDto, HttpServletRequest httpServletRequest) throws ParseException, IOException;

    List<RentalDto> getRentals();

    RentalDto getRental(int id) throws UnauthorizedException;

    void updateRental(RentalDto rentalDto);
}
