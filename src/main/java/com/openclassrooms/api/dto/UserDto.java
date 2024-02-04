package com.openclassrooms.api.dto;

import static com.openclassrooms.api.validators.ValidationGroups.Create;
import static com.openclassrooms.api.validators.ValidationGroups.Authenticate;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class UserDto {

    @Schema(description = "User id", example = "1")
    private Long id;
    @NotNull(groups = Create.class)
    @Schema(description = "Username", example = "John Doe")
    private String name;
    @NotNull(groups = {Create.class, Authenticate.class})
    @Email(message = "Please enter a valid e-mail address")
    @Schema(description = "User email", example = "johndoe@gmail.com")
    private String email;
    @NotNull(groups = {Create.class, Authenticate.class})
    @Schema(description = "User password", example = "*****")
    private String password;
    @Schema(description = "Date user created")
    private Date created_at;
    @Schema(description = "Date user updated")
    private Date updated_at;

}
