package com.yhyt.health;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;


@EnableTransactionManagement
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.yhyt.health.dao")
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)

public class SystemApplication extends HealthApplaction {
	private static Logger logger = LoggerFactory.getLogger(SystemApplication.class);
	
	@Bean(name="loadBalanced")
    @LoadBalanced
    public RestTemplate restTemplate(){
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(3000);
        requestFactory.setReadTimeout(15000);
        return new RestTemplate(requestFactory);
    }
	
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SystemApplication.class, args);
        logger.info("provider doctor 启动成功");
    }
}
