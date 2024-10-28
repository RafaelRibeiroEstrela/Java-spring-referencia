package com.example.springrabbitmq;

import com.example.springrabbitmq.consumers.Consumer;
import com.example.springrabbitmq.dtos.MessageDTO;
import com.example.springrabbitmq.producers.Producer;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sound.midi.Receiver;

@SpringBootApplication
@EnableRabbit
public class SpringRabbitmqApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringRabbitmqApplication.class, args);
	}

	@Autowired
	Producer producer;

	@Override
	public void run(String... args) throws Exception {
		MessageDTO messageDTO = new MessageDTO();
		messageDTO.setValue("Mensagem teste");
		producer.produce(messageDTO);
	}
}
