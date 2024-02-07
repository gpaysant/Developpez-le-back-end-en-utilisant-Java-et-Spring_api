package com.openclassrooms.api.services;

import com.openclassrooms.api.dto.RentalDto;
import com.openclassrooms.api.exceptions.UnauthorizedEmptyException;
import com.openclassrooms.api.models.Rental;
import com.openclassrooms.api.repository.RentalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<RentalDto> getRentals() {
        List<RentalDto> rentals = new ArrayList<RentalDto>();
        rentalRepository.findAll().forEach(rental -> {
            RentalDto rentalDto = convertToDto(rental);
            rentalDto.setOwner_id(rental.getUser().getId());
            rentals.add(rentalDto);

        });
        return rentals;
    }

    @Override
    public RentalDto getRental(int id) {
        Optional<Rental> rental = rentalRepository.findById((long)id);
        if (rental.isPresent()) {
            Rental rentalFound = rental.get();
            RentalDto rentalDto = convertToDto(rentalFound);
            rentalDto.setOwner_id(rentalFound.getUser().getId());
            return  rentalDto;
        }
        throw new UnauthorizedEmptyException();
    }

    private Rental convertToEntity(RentalDto rentalDto) throws ParseException {
        Rental rental = this.modelMapper.map(rentalDto, Rental.class);
        return rental;
    }

    private RentalDto convertToDto(Rental rental) {
        RentalDto rentalDto = this.modelMapper.map(rental, RentalDto.class);
        return rentalDto;
    }
}
