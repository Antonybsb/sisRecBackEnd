package com.example.sisrec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SisrecApplication {

	public static void main(String[] args) {
		SpringApplication.run(SisrecApplication.class, args);
//		System.out.println(new BCryptPasswordEncoder().encode("senha123"));
	}

}
