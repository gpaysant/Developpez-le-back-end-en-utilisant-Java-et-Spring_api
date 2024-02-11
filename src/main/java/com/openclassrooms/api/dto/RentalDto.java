package com.openclassrooms.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

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
    @JsonFormat(pattern="yyyy/MM/dd")
    private Date createDate;
    @JsonFormat(pattern="yyyy/MM/dd")
    private Date updateDate;

}
