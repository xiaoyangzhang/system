package com.yhyt.health;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.yhyt.health.spring.ResponseBodyWrapFactoryBean;

public abstract class HealthApplaction {
	
	@ConfigurationProperties("resultformat")
	@Bean
	public ResponseBodyWrapFactoryBean getResponseBodyWrap() {
		return new ResponseBodyWrapFactoryBean();
	}
	
//    @Bean(name="restTemplate")
//    public RestTemplate restTemplate(){
//        return new RestTemplate();
//    }
	
}
