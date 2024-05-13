package com.example.loadbalancer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class loadBalancerApplication {

	public static void main(String[] args) {
		SpringApplication.run(loadBalancerApplication.class, args);
	}

}
