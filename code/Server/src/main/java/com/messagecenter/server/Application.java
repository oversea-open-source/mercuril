package com.messagecenter.server;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	@Bean
	public Queue helloQueue() {
		return new Queue("hello", true);
	}

	@Bean
	public Sender mySender() {
		return new Sender();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
