package com.shockn745.domain.core;

import com.google.common.base.MoreObjects;
import com.shockn745.domain.application.driven.MarkdownParser;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Strings.nullToEmpty;

/**
 * @author Kempenich Florian
 */
public class BlogPost {

    public final int id;
    public final String title;
    public final String markdownContent;
    public final LocalDate date;
    public final List<String> tags;
    private final Summarizer summarizer;
    private final MarkdownParser markdownParser;

    BlogPost(int id, String title, String markdownContent, LocalDate date, List<String> tags, Summarizer summarizer, MarkdownParser markdownParser) {
        checkNotNull(summarizer);
        checkNotNull(markdownParser);
        checkNotNull(date);
        checkNotNull(tags);
        this.id = id;
        this.title = nullToEmpty(title);
        this.markdownContent = nullToEmpty(markdownContent);
        this.summarizer = summarizer;
        this.markdownParser = markdownParser;
        this.date = date;
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlogPost blogPost = (BlogPost) o;
        return id == blogPost.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        boolean shouldShorten = nullToEmpty(markdownContent).length() > 7;
        String shortenedPost = shouldShorten ? markdownContent.substring(0, 7) : markdownContent;
        return MoreObjects.toStringHelper(this)
                .add("title", title)
                .add("markdownContent", shortenedPost)
                .add("date", date)
                .add("tags", tags)
                .toString();
    }

    public String summarize(int charLimit) {
        return summarizer.getSummary(markdownContent, charLimit);
    }

    public String getHtml(int postId) {
        return markdownParser.toHtml(markdownContent, postId);
    }
}
