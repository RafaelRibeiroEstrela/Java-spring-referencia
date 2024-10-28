package com.example.springrabbitmq.producers;

import com.example.springrabbitmq.configs.MessagingConfig;
import com.example.springrabbitmq.dtos.MessageDTO;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class Producer {

    private final RabbitTemplate rabbitTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);

    public void produce(MessageDTO menssageDTO) {
        menssageDTO.setSendTime(LocalDateTime.now());
        rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE_NAME, MessagingConfig.ROUTING_KEY, menssageDTO);
        LOGGER.info("Mensagem enviada com sucesso");
    }
}
