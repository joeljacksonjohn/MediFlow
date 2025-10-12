package com.mediserve.pharmacyservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class PharmacyConfig {

	@Bean
	@LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
