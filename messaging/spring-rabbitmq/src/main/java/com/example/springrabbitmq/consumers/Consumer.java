package com.example.springrabbitmq.consumers;

import com.example.springrabbitmq.configs.MessagingConfig;
import com.example.springrabbitmq.dtos.MessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Consumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

    @RabbitListener(queues = MessagingConfig.QUEUE_NAME)
    public void consume(MessageDTO messageDTO) {
        messageDTO.setReceiveTime(LocalDateTime.now());
        LOGGER.info("Mensagem recebida: {}", messageDTO);
    }
}
