package com.shockn745;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class WebAppApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(WebAppApplication.class, args);
        System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = context.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }

    @Bean
    @Qualifier("what_default")
    public String getWhatDefault() {
        return "Smoke ?";
    }
}
