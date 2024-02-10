package com.openclassrooms.api.controllers;

import com.openclassrooms.api.dto.InputMessageDto;
import com.openclassrooms.api.exceptions.UnauthorizedException;
import com.openclassrooms.api.responses.MyResponseMessageObject;
import com.openclassrooms.api.services.MessageService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.HashMap;

@RestController
public class MessageController {

    @Autowired
    MessageService messageService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MyResponseMessageObject.class))),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PostMapping("/messages")
    public ResponseEntity<?> writeMessage(@Valid @RequestBody InputMessageDto inputMessageDto, BindingResult bindingResult) throws ParseException, UnauthorizedException {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new HashMap<>(), HttpStatus.BAD_REQUEST);
        }
        messageService.createMessage(inputMessageDto);
        MyResponseMessageObject myResponseMessageObject = new MyResponseMessageObject();
        myResponseMessageObject.setMessage("Message send with success");
        return ResponseEntity.ok(myResponseMessageObject);
    }
}
