package com.prince.rest;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class RestApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}

	//Internationalization concept-

//	@Bean
//	public LocaleResolver configureLocaleResolver(){
//		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
//		sessionLocaleResolver.setDefaultLocale(Locale.US);
//		return sessionLocaleResolver;
//	}

	@Bean
	public LocaleResolver configureLocaleResolver(){
		AcceptHeaderLocaleResolver sessionLocaleResolver = new AcceptHeaderLocaleResolver();
		sessionLocaleResolver.setDefaultLocale(Locale.US);
		return sessionLocaleResolver;
	}

	//Internationalization Concept continue...

	//This no longer required as now 'basename' is set in application.properties-

//	@Bean
//	public ResourceBundleMessageSource messageSource(){
//		ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
//		resourceBundleMessageSource.setBasename("msg");
//		return resourceBundleMessageSource;
//	}

}
