package com.promineotech.StackOverFlowAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.promineotech.StackOverFlowAPI")
@SpringBootApplication
public class App {
	public static void main(String[] args) {

		SpringApplication.run(App.class, args);

	}
}
