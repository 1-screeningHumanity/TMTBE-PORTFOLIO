package com.TMT.TMT_BE_PortFolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class PortFolioApplication {

	public static void main(String[] args) {

		SpringApplication.run(PortFolioApplication.class, args);
	}

}
