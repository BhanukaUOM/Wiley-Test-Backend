package com.sadheera.wileytest;

import com.sadheera.wileytest.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class WileyTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(WileyTestApplication.class, args);
	}

}
