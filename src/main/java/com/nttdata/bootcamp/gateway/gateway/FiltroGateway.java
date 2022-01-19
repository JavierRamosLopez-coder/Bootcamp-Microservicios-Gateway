package com.nttdata.bootcamp.gateway.gateway;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

@Log4j2
@Configuration
public class FiltroGateway {

	@Bean
	@Order(-1)
	public GlobalFilter a() {
		return(exchange, chain) ->{
			log.info("En la entrada es el primero");
			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				log.info("En la salida es el último");
			}));
		};
	}
	
	@Bean
	@Order(0)
	public GlobalFilter b() {
		return(exchange, chain) ->{
			log.info("En la entrada es el segundo");
			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				log.info("En la salida es el segundo");
			}));
		};
	}
	
	@Bean
	@Order(1)
	public GlobalFilter c() {
		return(exchange, chain) ->{
			log.info("En la entrada es el tercero");
			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				log.info("En la salida es el primero");
			}));
		};
	}
	
}
