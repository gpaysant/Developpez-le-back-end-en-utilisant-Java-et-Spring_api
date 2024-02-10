package com.openclassrooms.api.services;

import com.openclassrooms.api.dto.InputMessageDto;
import com.openclassrooms.api.exceptions.UnauthorizedException;

import java.text.ParseException;

public interface MessageService {
    void createMessage(InputMessageDto inputMessageDto) throws ParseException, UnauthorizedException;
}
