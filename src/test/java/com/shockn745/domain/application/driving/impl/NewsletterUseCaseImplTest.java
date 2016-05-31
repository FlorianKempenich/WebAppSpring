package com.shockn745.domain.application.driving.impl;

import com.shockn745.domain.application.driven.NewsletterRepository;
import com.shockn745.domain.application.driving.NewsletterUseCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

/**
 * @author Kempenich Florian
 */
public class NewsletterUseCaseImplTest {

    @Mock
    NewsletterRepository repository;

    NewsletterUseCase newsletterUseCase;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        newsletterUseCase = new NewsletterUseCaseImpl(repository);
    }

    @Test
    public void saveToNewsletter_validEmail() throws Exception {
        /*
         * For now: Do not test for invalid email. Validation is done by presentation layer, in the backend
         * (Spring validator)
         */
        newsletterUseCase.addToNewsletter("test@test.com");
        verify(repository).save("test@test.com");
    }
}