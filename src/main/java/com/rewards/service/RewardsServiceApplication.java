package com.rewards.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.rewards.service")
public class RewardsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RewardsServiceApplication.class, args);
	}
}
