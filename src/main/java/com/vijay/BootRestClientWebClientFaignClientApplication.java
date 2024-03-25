package com.vijay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BootRestClientWebClientFaignClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootRestClientWebClientFaignClientApplication.class, args);
	}

}
