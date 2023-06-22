package com.denuncieagora.denuncie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DenuncieApplication {

	public static void main(String[] args) {
		SpringApplication.run(DenuncieApplication.class, args);
	}

}
