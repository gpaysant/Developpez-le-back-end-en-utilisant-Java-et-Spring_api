package com.openclassrooms.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class UserDto {

    @Schema(description = "User id", example = "1")
    private Long id;
    @NotNull
    @Schema(description = "Username", example = "John Doe")
    private String name;
    @NotNull
    @Schema(description = "User email", example = "johndoe@gmail.com")
    private String email;
    @NotNull
    @Schema(description = "User password", example = "*****")
    private String password;
    @Schema(description = "Date user created")
    private Date created_at;
    @Schema(description = "Date user updated")
    private Date updated_at;

}
