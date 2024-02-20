package com.openclassrooms.api.services;

import com.openclassrooms.api.dto.InputMessageDto;
import com.openclassrooms.api.exceptions.UnauthorizedException;

public interface MessageService {
    void createMessage(InputMessageDto inputMessageDto) throws UnauthorizedException;
}
