package com.mx.UsuarioMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UsuarioMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsuarioMsApplication.class, args);
	}

}
