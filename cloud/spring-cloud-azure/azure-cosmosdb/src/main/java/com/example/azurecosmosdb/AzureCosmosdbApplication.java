package com.example.azurecosmosdb;

import com.example.azurecosmosdb.models.User;
import com.example.azurecosmosdb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class AzureCosmosdbApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AzureCosmosdbApplication.class, args);
	}

	@Autowired
	private UserRepository repository;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Operacoes");
		/*
		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setFirstName("Carlos");
		user.setLastName("Silva");
		user.setAddress("endereco");
		repository.save(user);

		 */
		repository.findByFirstName("Carlos").forEach(System.out::println);
	}
}
