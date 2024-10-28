package com.example.springkafka.producers;

import com.example.springkafka.models.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class MessageProducer {

    @Value("${kafka.topic}")
    private String kafkaTopic;

    private final KafkaTemplate<String, Object> kafkaTemplate;


    public MessageProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public String sendMessage(Message message) {
        try {
            return kafkaTemplate.send(kafkaTopic, message).get().getProducerRecord().toString();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
