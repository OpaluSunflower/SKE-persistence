package com.example.fotowoltaika;

import com.example.fotowoltaika.domain.User;
//import com.example.fotowoltaika.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.format.DateTimeFormatter;


@SpringBootApplication
@EnableScheduling
public class FotowoltaikaApplication {

	//private final UserService userService;

	/*public FotowoltaikaApplication(UserService userService) {
		this.userService = userService;
	}*/

	public static void main(String[] args) {
		SpringApplication.run(FotowoltaikaApplication.class, args);
	}

	@Bean
	DateTimeFormatter dtf()
	{
		return DateTimeFormatter.ofPattern("yyyy-MM-dd");
	}

}


