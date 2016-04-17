package com.shockn745.domain.core;

import java.util.Objects;

/**
 * @author Kempenich Florian
 */
public class BlogPost {

    public final String title;
    public final String post;

    public BlogPost(String title, String post) {
        this.title = title;
        this.post = post;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlogPost blogPost = (BlogPost) o;
        return Objects.equals(title, blogPost.title) &&
                Objects.equals(post, blogPost.post);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, post);
    }
}
