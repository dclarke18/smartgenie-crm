/*
 * Copyright BLC IT Services Ltd 2015
 */
package uk.co.blc_services.smartgenie.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * Creates and serves the REST interface using Spring magic.
 * @author dave.clarke@blc-services.co.uk
 * 
 */
@SpringBootApplication
@EntityScan("uk.co.blc_services.smartgenie.domain")
@ComponentScan(value = {"uk.co.blc_services.smartgenie.web", "uk.co.blc_services.smartgenie.rest"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
