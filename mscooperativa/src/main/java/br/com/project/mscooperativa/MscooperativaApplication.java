package br.com.project.mscooperativa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MscooperativaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MscooperativaApplication.class, args);
	}

}
