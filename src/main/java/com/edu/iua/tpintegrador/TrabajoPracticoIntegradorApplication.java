package com.edu.iua.tpintegrador;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.edu.iua.tpintegrador.controller.ChoferesRestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

@SpringBootApplication
@ComponentScan("com.edu.iua.tpintegrador")

public class TrabajoPracticoIntegradorApplication extends SpringBootServletInitializer implements CommandLineRunner{

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	public static void main(String[] args) {
		SpringApplication.run(TrabajoPracticoIntegradorApplication.class, args);
	}
	
	@Value("${spring.datasource.url}")
	private String springDatasourceUrl;

	@Override
	public void run(String... args) throws Exception {
		log.info("DataSource URL: {}", springDatasourceUrl);
	}

}
