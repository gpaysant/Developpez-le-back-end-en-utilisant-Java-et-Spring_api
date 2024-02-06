package com.openclassrooms.api.services;

import com.openclassrooms.api.dto.RentalDto;
import com.openclassrooms.api.models.Rental;

import java.text.ParseException;

public interface RentalService {
    void createNewRental(RentalDto rentalDto) throws ParseException;

    Rental saveRental(RentalDto rentalDto) throws ParseException;
}
