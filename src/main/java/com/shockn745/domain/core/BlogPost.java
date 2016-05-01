package com.shockn745.domain.core;

import java.util.Objects;

import static com.google.common.base.Strings.nullToEmpty;

/**
 * @author Kempenich Florian
 */
public class BlogPost {

    public final String title;
    public final String post;

    public BlogPost(String title, String post) {
        this.title = nullToEmpty(title);
        this.post = nullToEmpty(post);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, post);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlogPost blogPost = (BlogPost) o;
        return Objects.equals(title, blogPost.title) &&
                Objects.equals(post, blogPost.post);
    }

    public String summarize(int charLimit) {

        String truncatedInLength = truncatePostInLength(charLimit);

        return truncateAtFirstTitle(truncatedInLength);
    }

    private String truncateAtFirstTitle(String truncated) {
        int indexOfFirstTitle = truncated.indexOf("#");
        if (indexOfFirstTitle != -1) {
            return truncated.substring(0, indexOfFirstTitle);
        } else {
            return truncated;
        }
    }

    private String truncatePostInLength(int charLimit) {
        if (post.length() > charLimit) {
            return post.substring(0, charLimit);
        } else {
            return post;
        }
    }
}
