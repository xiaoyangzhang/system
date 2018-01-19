package com.yhyt.health;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@EnableTransactionManagement
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.yhyt.health.dao")
public class EasyDeployWarApplication extends HealthApplaction{
	private static Logger logger = LoggerFactory.getLogger(EasyDeployWarApplication.class);
	
	@Bean
	public RestTemplate restTemplate(){
	   return new RestTemplate();
	}

    public static void main(String[] args) {
        SpringApplication.run(EasyDeployWarApplication.class, args);
        logger.info("provider doctor 启动成功");
    }
}