package com.shockn745.domain.application.driving;

/**
 * Return the message to display on the "What" webpage.
 * Used as a template for the architecture, and to hide easter eggs.
 *
 * @author Kempenich Florian
 */
public interface GetWhatMessageUseCase {

    String execute(String input);
}
