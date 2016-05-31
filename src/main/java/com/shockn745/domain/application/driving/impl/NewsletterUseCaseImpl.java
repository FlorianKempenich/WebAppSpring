package com.shockn745.domain.application.driving.impl;

import com.shockn745.domain.application.driven.NewsletterRepository;
import com.shockn745.domain.application.driving.NewsletterUseCase;

/**
 * @author Kempenich Florian
 */
public class NewsletterUseCaseImpl implements NewsletterUseCase {

    private final NewsletterRepository repository;

    public NewsletterUseCaseImpl(NewsletterRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addToNewsletter(String emailAddress) {
        repository.save(emailAddress);
    }
}
