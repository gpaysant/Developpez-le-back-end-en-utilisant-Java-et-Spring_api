package com.openclassrooms.api.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RentalDto {

    private Long id;
    private String name;
    private String surface;
    private String price;
    private String picture;
    private String description;
    private UserDto userDto;
    private Date created_at;
    private Date updated_at;
}
