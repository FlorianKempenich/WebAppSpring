package com.shockn745.domain.application.driving.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

/**
 * @author Kempenich Florian
 */
@Entity
public class BlogPostDTO {

    public static final Long NO_ID = -1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = NO_ID;
    private String title = "";
    private String post = "";

    public static BlogPostDTO make(String title, String post, long id) {
        BlogPostDTO result = new BlogPostDTO();
        result.setId(id);
        result.setPost(post);
        result.setTitle(title);
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        String shortenedPost = post.length() < 20 ? post : post.substring(0, 19);
        return "Post : id=" + id + " | title=\"" + title + "\" | text=\"" + shortenedPost + "\"";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlogPostDTO that = (BlogPostDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(title, that.title) &&
                Objects.equals(post, that.post);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, post);
    }
}
