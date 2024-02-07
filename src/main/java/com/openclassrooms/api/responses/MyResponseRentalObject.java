package com.openclassrooms.api.responses;

import com.openclassrooms.api.dto.RentalDto;
import lombok.Data;

import java.util.List;

@Data
public class MyResponseRentalObject {

    List<RentalDto> rentals;
}
