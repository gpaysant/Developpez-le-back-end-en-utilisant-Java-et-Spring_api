package com.openclassrooms.api.services;

import com.openclassrooms.api.dto.RentalDto;
import com.openclassrooms.api.models.Rental;
import com.openclassrooms.api.repository.RentalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.util.Date;

@Service
public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository;
    private final ModelMapper modelMapper;

    public RentalServiceImpl(RentalRepository rentalRepository, ModelMapper modelMapper) {
        this.rentalRepository = rentalRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public void createNewRental(RentalDto rentalDto) throws ParseException {
        rentalDto.setCreated_at(Date.from(Instant.now()));
        saveRental(rentalDto);
    }

    @Override
    public Rental saveRental(RentalDto rentalDto) throws ParseException {
        rentalDto.setUpdated_at(Date.from(Instant.now()));
        Rental rental = convertToEntity(rentalDto);
        Rental saveRental = rentalRepository.save(rental);
        return saveRental;
    }

    private Rental convertToEntity(RentalDto rentalDto) throws ParseException {
        Rental rental = this.modelMapper.map(rentalDto, Rental.class);
        return rental;
    }
}
