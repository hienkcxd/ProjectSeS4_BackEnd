package com.example.BachEnd_Ses4;

import com.example.BachEnd_Ses4.model.System.Role;
import com.example.BachEnd_Ses4.model.System.User;
import com.example.BachEnd_Ses4.service.system.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class BachEndSes4Application {

	public static void main(String[] args) {
		SpringApplication.run(BachEndSes4Application.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));

			userService.saveUser(new User(null, "Hien Dang", "hien", "email.admin1@gmail.com","0981815414","123", new ArrayList<>()));
			userService.saveUser(new User(null, "Ly Truc", "ly", "email.admin2@gmail.com","341232123","123", new ArrayList<>()));
			userService.saveUser(new User(null, "Coop", "coop", "email.coop@gmail.com","5542221321","123", new ArrayList<>()));
			userService.saveUser(new User(null, "Lotte", "lotte", "email.lotte@gmail.com","9928172321","123", new ArrayList<>()));

			userService.addRoleToUser("Hien", "ROLE_ADMIN");
			userService.addRoleToUser("Ly", "ROLE_ADMIN");
			userService.addRoleToUser("coop", "ROLE_USER");
			userService.addRoleToUser("lotte", "ROLE_USER");
		};
	}

}
