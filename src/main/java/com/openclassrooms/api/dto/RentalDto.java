package com.openclassrooms.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class RentalDto {

    @Schema(description = "Rental id", example = "1")
    private Long id;
    @Schema(description = "Rental name", example = "House1")
    private String name;
    @Schema(description = "Rental surface", example = "200")
    private String surface;
    @Schema(description = "Rental price", example = "200000")
    private String price;
    @Schema(description = "Rental picture", example = " http://localhost:3001/api/uploads/screenshot_house.jpg")
    private String picture;
    @Schema(description = "Rental description", example = "Beautiful house with a lot of windows and quiet place")
    private String description;
    @JsonIgnore
    private UserDto userDto;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "Rental owner id", example = "2")
    private Long owner_id;
    @JsonFormat(pattern="yyyy/MM/dd")
    @Schema(description = "creation date rental", example = "11/02/2024")
    @JsonProperty("created_at")
    private Date createDate;
    @JsonFormat(pattern="yyyy/MM/dd")
    @Schema(description = "update date rental", example = "12/02/2024")
    @JsonProperty("updated_at")
    private Date updateDate;

}
