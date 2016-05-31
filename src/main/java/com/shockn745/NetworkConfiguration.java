package com.shockn745;

import com.shockn745.domain.application.driven.NewsletterRepository;
import com.shockn745.domain.application.driving.NewsletterUseCase;
import com.shockn745.domain.application.driving.impl.NewsletterUseCaseImpl;
import com.shockn745.network.SpringNewsletterRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Kempenich Florian
 */
@Configuration
public class NetworkConfiguration {

    @Bean
    public NewsletterUseCase getNewsletterUseCase(NewsletterRepository repository) {
        return new NewsletterUseCaseImpl(repository);
    }

    @Bean
    public NewsletterRepository getNewsletterRepository() {
        return new SpringNewsletterRepositoryImpl();
    }
}