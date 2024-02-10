package com.openclassrooms.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class MessageDto {

    private Long id;
    @JsonIgnore
    private RentalDto rentalDto;
    @JsonIgnore
    private UserDto userDto;
    private String message;
    private Date createDate;
    private Date updateDate;
}
