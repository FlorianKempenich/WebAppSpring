package com.shockn745.domain.core;

import com.shockn745.domain.application.driven.MarkdownParser;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Strings.nullToEmpty;

/**
 * @author Kempenich Florian
 */
public class BlogPost {

    private final Summarizer summarizer;
    private final MarkdownParser markdownParser;

    public final String title;
    public final String markdownContent;

    BlogPost(String title, String markdownContent, Summarizer summarizer, MarkdownParser markdownParser) {
        checkNotNull(summarizer);
        checkNotNull(markdownParser);
        this.title = nullToEmpty(title);
        this.markdownContent = nullToEmpty(markdownContent);
        this.summarizer = summarizer;
        this.markdownParser = markdownParser;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, markdownContent);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlogPost blogPost = (BlogPost) o;
        return Objects.equals(title, blogPost.title) &&
                Objects.equals(markdownContent, blogPost.markdownContent);
    }

    public String summarize(int charLimit) {
        return summarizer.getSummary(markdownContent, charLimit);
    }

    public String getHtml() {
        return markdownParser.toHtml(markdownContent);
    }
}
