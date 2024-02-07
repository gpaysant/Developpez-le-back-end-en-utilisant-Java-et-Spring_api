package com.openclassrooms.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
public class RentalDto {

    private Long id;
    private String name;
    private String surface;
    private String price;
    private String picture;
    private String description;
    @JsonIgnore
    private UserDto userDto;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long owner_id;
    private Date created_at;
    private Date updated_at;

}
