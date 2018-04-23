package com.skipthediches.challenge.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.web.reactive.DispatcherHandler;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class GatewayApplication {

	@Bean
	public DispatcherHandler dispatcherHandler(ApplicationContext context) {
		return new DispatcherHandler(context);
	}

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
}
