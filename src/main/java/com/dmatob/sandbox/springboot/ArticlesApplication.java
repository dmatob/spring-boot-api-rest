package com.dmatob.sandbox.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = { "classpath:application-persistence.properties" })
public class ArticlesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArticlesApplication.class, args);
	}

}
