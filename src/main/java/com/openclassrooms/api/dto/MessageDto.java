package com.openclassrooms.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("created_at")
    private Date createDate;
    @JsonProperty("updated_at")
    private Date updateDate;
}
