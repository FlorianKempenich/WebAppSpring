package com.shockn745.presentation.model;

/**
 * @author Kempenich Florian
 */
public class PostSummary {

    public final int id;
    public final String title;
    public final String summary;

    public PostSummary(int id, String title, String summary) {
        this.id = id;
        this.title = title;
        this.summary = summary;
    }
}
