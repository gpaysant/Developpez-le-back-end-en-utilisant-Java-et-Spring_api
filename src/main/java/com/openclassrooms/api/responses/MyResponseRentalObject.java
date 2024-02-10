package com.openclassrooms.api.responses;

import com.openclassrooms.api.dto.RentalDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class MyResponseRentalObject {
    List<RentalDto> rentals;
}
