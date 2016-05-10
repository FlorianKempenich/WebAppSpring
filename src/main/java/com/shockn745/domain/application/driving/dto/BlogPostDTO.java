package com.shockn745.domain.application.driving.dto;

import com.google.common.base.MoreObjects;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.google.common.base.Strings.nullToEmpty;

/**
 * @author Kempenich Florian
 */
@Entity
public class BlogPostDTO {

    public static final int NO_ID = -1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id = NO_ID;
    private String title = "";
    @Lob
    private String markdownPost = "";
    private LocalDate date = LocalDate.MIN;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="listOfTags")
    private List<String> tags = new ArrayList<>();

    public final static BlogPostDTO EMPTY = BlogPostDTO.make("", "", 0, LocalDate.MIN, new ArrayList<>());

    public static BlogPostDTO make(String title, String post, int id, LocalDate date, List<String> tags) {
        BlogPostDTO result = new BlogPostDTO();
        result.setId(id);
        result.setMarkdownPost(post);
        result.setTitle(title);
        result.setDate(date);
        result.setTags(tags);
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarkdownPost() {
        return markdownPost;
    }

    public void setMarkdownPost(String post) {
        this.markdownPost = post;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        boolean shouldShorten = nullToEmpty(markdownPost).length() > 7;
        String shortenedPost = shouldShorten ? markdownPost.substring(0, 7) : markdownPost;

        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("title", title)
                .add("markdownPost", shortenedPost)
                .add("date", date)
                .add("tags", tags)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlogPostDTO that = (BlogPostDTO) o;
        return id == that.id &&
                Objects.equals(title, that.title) &&
                Objects.equals(markdownPost, that.markdownPost) &&
                Objects.equals(date, that.date) &&
                Objects.equals(tags, that.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, markdownPost, date, tags);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        if (date != null) {
            this.date = date;
        } else {
            this.date = LocalDate.MIN;
        }
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        if (tags != null) {
            this.tags = tags;
        } else {
            this.tags = new ArrayList<>();
        }
    }
}
