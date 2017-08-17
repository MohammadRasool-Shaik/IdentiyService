package org.rash.identity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Application BootStart
 *
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = {"org.rash.identity.model", "org.rash.identity.repository"})
@PropertySource("classpath:db-config.properties")
@EnableDiscoveryClient
public class IdentityServerBootStart {
	public static void main(String[] args) {
		System.setProperty("spring.config.name", "identity-server");
		SpringApplication.run(IdentityServerBootStart.class, args);
	}
}
