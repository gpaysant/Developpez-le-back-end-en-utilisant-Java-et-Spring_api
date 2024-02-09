package com.openclassrooms.api.services;

import com.openclassrooms.api.dto.InputMessageDto;

import java.text.ParseException;

public interface MessageService {
    boolean createMessage(InputMessageDto inputMessageDto) throws ParseException;
}
