package com.openclassrooms.api.services;

import com.openclassrooms.api.dto.InputMessageDto;
import com.openclassrooms.api.dto.MessageDto;
import com.openclassrooms.api.dto.RentalDto;
import com.openclassrooms.api.dto.UserDto;
import com.openclassrooms.api.exceptions.UnauthorizedException;
import com.openclassrooms.api.models.Message;
import com.openclassrooms.api.repository.MessageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class MessageServiceImpl implements MessageService {

    private final RentalService rentalService;
    private final UserService userService;
    private final MessageRepository messageRepository;
    private final ModelMapper modelMapper;

    public MessageServiceImpl(RentalService rentalService, UserService userService, MessageRepository messageRepository, ModelMapper modelMapper) {
        this.rentalService = rentalService;
        this.userService = userService;
        this.messageRepository = messageRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createMessage(InputMessageDto inputMessageDto) throws UnauthorizedException {
        MessageDto messageDto = convertInputDtoToDto(inputMessageDto);
        Message message = this.modelMapper.map(messageDto, Message.class);
        messageRepository.save(message);
    }

    private MessageDto convertInputDtoToDto(InputMessageDto inputMessageDto) throws UnauthorizedException {
        MessageDto messageDto = new MessageDto();
        RentalDto rentalDto = rentalService.getRental(inputMessageDto.getRental_id());
        messageDto.setRentalDto(rentalDto);
        UserDto userDto = userService.getUser(inputMessageDto.getUser_id());
        messageDto.setUserDto(userDto);
        messageDto.setMessage(inputMessageDto.getMessage());
        messageDto.setCreated_at(Date.from(Instant.now()));
        messageDto.setUpdated_at(Date.from(Instant.now()));
        return messageDto;
    }

}
