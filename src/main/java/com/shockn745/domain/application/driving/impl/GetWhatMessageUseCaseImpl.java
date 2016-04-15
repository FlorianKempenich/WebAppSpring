package com.shockn745.domain.application.driving.impl;

import com.shockn745.data.SecretRepository;
import com.shockn745.domain.application.driving.GetWhatMessageUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author Kempenich Florian
 */
@Component
public class GetWhatMessageUseCaseImpl implements GetWhatMessageUseCase {

    private final String defaultString;
    private final SecretRepository repository;

    @Autowired
    public GetWhatMessageUseCaseImpl(SecretRepository repository, @Qualifier("what_default") String defaultString) {
        this.defaultString = defaultString;
        this.repository = repository;
    }

    @Override
    public String execute(String input) {
        if (isEmpty(input)) {
            return defaultString;
        } else if (isGiorgos(input)) {
            return repository.getGiorgosSecret();
        } else {
            return input;
        }
    }

    private boolean isGiorgos(String candidate) {
        return candidate.trim().toLowerCase().contains("giorgos");
    }

    private boolean isEmpty(String input) {
        return input == null || input.isEmpty();
    }
}