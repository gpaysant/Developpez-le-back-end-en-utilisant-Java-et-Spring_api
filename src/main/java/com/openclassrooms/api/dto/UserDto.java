package com.openclassrooms.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.openclassrooms.api.validators.ValidationGroups.Authenticate;
import com.openclassrooms.api.validators.ValidationGroups.Create;
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @JsonFormat(pattern="yyyy/MM/dd")
    @Schema(description = "Date user created")
    @JsonProperty("created_at")
    private Date createDate;
    @JsonFormat(pattern="yyyy/MM/dd")
    @Schema(description = "Date user updated")
    @JsonProperty("updated_at")
    private Date updateDate;

}
