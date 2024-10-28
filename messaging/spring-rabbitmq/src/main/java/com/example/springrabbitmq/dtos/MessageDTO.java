package com.example.springrabbitmq.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageDTO {

    private String value;
    private LocalDateTime sendTime;
    private LocalDateTime receiveTime;

}
