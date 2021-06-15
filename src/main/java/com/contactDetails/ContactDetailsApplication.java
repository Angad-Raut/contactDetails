package com.contactDetails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * @author angad
 * Date : 14 June 2021
 * This is the entry point of application.
 */
@SpringBootApplication
public class ContactDetailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactDetailsApplication.class, args);
	}
	
	@Bean
    public ResourceBundleMessageSource messageSource(){
        ResourceBundleMessageSource source=new ResourceBundleMessageSource();
        source.setBasenames("i18n/validationsMessages","i18n/errorMessages");
        source.setUseCodeAsDefaultMessage(true);
        return source;
    }

}
