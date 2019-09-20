package com.techprimers.springbatchexample1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.annotation.WebServlet;

@SpringBootApplication
public class SpringBatchExample1Application {

	@Bean
	public ServletRegistrationBean h2servletRegistration() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new org.h2.server.web.WebServlet());
		registration.addUrlMappings("/console/*");
		return registration;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchExample1Application.class, args);
	}
}
