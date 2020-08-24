package com.daimlerpoi.getmypois;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class GetmypoisApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetmypoisApplication.class, args);
	}

}
