package com.openclassrooms.api.services;

import com.openclassrooms.api.dto.RentalDto;
import com.openclassrooms.api.exceptions.UnauthorizedException;
import com.openclassrooms.api.models.Rental;
import com.openclassrooms.api.repository.RentalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public Rental saveRental(RentalDto rentalDto) {
        rentalDto.setUpdated_at(Date.from(Instant.now()));
        Rental rental = this.modelMapper.map(rentalDto, Rental.class);
        return rentalRepository.save(rental);
    }

    @Override
    public List<RentalDto> getRentals() {
        List<RentalDto> rentals = new ArrayList<RentalDto>();
        rentalRepository.findAll().forEach(rental -> {
            RentalDto rentalDto = this.modelMapper.map(rental, RentalDto.class);
            rentalDto.setOwner_id(rental.getUser().getId());
            rentals.add(rentalDto);

        });
        return rentals;
    }

    @Override
    public RentalDto getRental(int id) throws UnauthorizedException {
        return rentalRepository.findById((long)id).map(
                rental -> {
                    RentalDto rentalDto = this.modelMapper.map(rental, RentalDto.class);
                    rentalDto.setOwner_id(rental.getUser().getId());
                    return rentalDto;
                }
        ).orElseThrow(UnauthorizedException::new);
    }

    @Override
    public void updateRental(RentalDto rentalDto) {
        rentalRepository.findById(rentalDto.getId()).
            ifPresent(rental -> {
                rental.setName(rentalDto.getName());
                rental.setSurface(rentalDto.getSurface());
                rental.setPrice(rentalDto.getPrice());
                rental.setDescription(rentalDto.getDescription());
                rental.setUpdated_at(java.sql.Date.valueOf(LocalDate.now()));
                rentalRepository.save(rental);
            });
    }
}
