package com.openclassrooms.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InputMessageDto {
    @NotNull
    private int rental_id;
    @NotNull
    private int user_id;
    @NotNull
    private String message;
}
