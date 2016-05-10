package com.shockn745.domain.core;

import com.shockn745.domain.application.driven.MarkdownParser;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Kempenich Florian
 */
public class BlogPostFactory {

    private final MarkdownParser markdownParser;

    public BlogPostFactory(MarkdownParser markdownParser) {
        this.markdownParser = markdownParser;
    }

    public BlogPost make(String title, String markdownContent, LocalDate date, List<String> tags) {
        Summarizer summarizer = new Summarizer();
        return new BlogPost(title, markdownContent, date, tags, summarizer, markdownParser);
    }

}
