package com.bitacademy.mysite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication  // @Configuration, @ComponentScan 기능 갖고 있음
public class MySiteApplication {
	public static void main(String[] args) {
		SpringApplication.run(MySiteApplication.class, args);
	}
}
