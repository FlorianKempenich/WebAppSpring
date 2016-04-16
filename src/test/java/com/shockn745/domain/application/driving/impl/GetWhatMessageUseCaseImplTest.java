package com.shockn745.domain.application.driving.impl;

import com.shockn745.domain.application.driven.SecretRepository;
import com.shockn745.domain.application.driving.GetWhatMessageUseCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author Kempenich Florian
 */
public class GetWhatMessageUseCaseImplTest {

    private GetWhatMessageUseCase getWhatMessageUseCase;
    private String defaultString = "Default";

    @Mock
    SecretRepository secretRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        getWhatMessageUseCase = new GetWhatMessageUseCaseImpl(secretRepository, defaultString);
    }

    @Test
    public void null_returnDefault() throws Exception {
        String result = getWhatMessageUseCase.execute(null);
        assertEquals(defaultString, result);
    }

    @Test
    public void empty_returnDefault() throws Exception {
        String result = getWhatMessageUseCase.execute("");
        assertEquals(defaultString, result);
    }

    @Test
    public void anything_exceptGiorgos_returnInput() throws Exception {
        String input = "Hello :D";
        String result = getWhatMessageUseCase.execute(input);
        assertEquals(input, result);
    }

    @Test
    public void giorgos_returns_giorgosSecret() throws Exception {
        String giorgosSecret = "secret :D :D";
        when(secretRepository.getGiorgosSecret()).thenReturn(giorgosSecret);

        assertEquals(giorgosSecret, getWhatMessageUseCase.execute("giorgos"));
        assertEquals(giorgosSecret, getWhatMessageUseCase.execute(" giorgos"));
        assertEquals(giorgosSecret, getWhatMessageUseCase.execute(" giOrgos"));
        assertEquals(giorgosSecret, getWhatMessageUseCase.execute("giorgos "));
        assertEquals(giorgosSecret, getWhatMessageUseCase.execute("Hello how are you giorgos!!!"));

    }
}