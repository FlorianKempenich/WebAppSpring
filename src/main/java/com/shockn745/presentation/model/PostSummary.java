package com.shockn745.presentation.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Kempenich Florian
 */
public class PostSummary {

    public final int id;
    public final String title;
    public final String summary;
    public final String date;

    public PostSummary(int id, String title, String summary, LocalDate date) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.date = formatDate(date);
    }

    private String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, YYYY");
        return date.format(formatter);
    }
}
