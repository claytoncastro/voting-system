package br.com.project.mscontabilizador;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableRabbit
@EnableEurekaClient
@SpringBootApplication
public class MscontabilizadorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MscontabilizadorApplication.class, args);
	}

}
