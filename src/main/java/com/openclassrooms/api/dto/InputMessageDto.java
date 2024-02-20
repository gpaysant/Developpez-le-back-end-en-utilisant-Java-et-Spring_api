package com.openclassrooms.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InputMessageDto {

    @NotNull
    @Schema(description = "Rental id", example = "1")
    private int rental_id;
    @NotNull
    @Schema(description = "User id", example = "3")
    private int user_id;
    @NotNull
    @Schema(description = "Message", example = "A lot of space in this house for my family")
    private String message;
}
