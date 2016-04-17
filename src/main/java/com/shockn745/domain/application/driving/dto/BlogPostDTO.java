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
    private String post = "";

    public static BlogPostDTO make(String post) {
        BlogPostDTO result = new BlogPostDTO();
        result.setPost(post);
        return result;
    }

    public static BlogPostDTO make(String post, long id) {
        BlogPostDTO result = make(post);
        result.setId(id);
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

    @Override
    public String toString() {
        String shortenedPost = post.length() < 20 ? post : post.substring(0, 19);
        return "Post : id=" + id + " | text=\"" + shortenedPost + "\"";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlogPostDTO that = (BlogPostDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(post, that.post);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, post);
    }
}
