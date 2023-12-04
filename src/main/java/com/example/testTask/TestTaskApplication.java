package com.example.testTask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.testTask.repositorys")
public class TestTaskApplication {
	public static void main(String[] args) {
		SpringApplication.run(TestTaskApplication.class, args);
	}

}
