package com.shockn745.domain.core;

/**
 * @author Kempenich Florian
 */
public class BlogPostFactory {


    public BlogPost make(String title, String markdownContent) {
        Summarizer summarizer = new Summarizer();

        return new BlogPost(title, markdownContent, summarizer);
    }

}
