package com.consoltant.consoltant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ConsoltantApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsoltantApplication.class, args);
	}

}
