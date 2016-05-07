package com.shockn745;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebAppApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(WebAppApplication.class, args);
    }

    @Bean
    @Qualifier("what_default")
    public String getWhatDefault() {
        return "Smoke ?";
    }
}
