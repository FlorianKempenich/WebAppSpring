package com.shockn745.domain.core;

import com.shockn745.domain.application.driven.MarkdownParser;

/**
 * @author Kempenich Florian
 */
public class BlogPostFactory {

    private final MarkdownParser markdownParser;

    public BlogPostFactory(MarkdownParser markdownParser) {
        this.markdownParser = markdownParser;
    }

    public BlogPost make(String title, String markdownContent) {
        Summarizer summarizer = new Summarizer();
        return new BlogPost(title, markdownContent, summarizer, markdownParser);
    }

}
