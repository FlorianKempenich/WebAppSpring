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
        String truncatedAtFirstTitle = truncateAtFirstTitle(truncatedInLength);

        return addEllipsis(truncatedAtFirstTitle);
    }

    private String addEllipsis(String text) {
        text = removeSpecialCharacterAtTheEnd(text);
        return text + " . . .";
    }

    /**
     * Removes the following characters from the end of the string : " ", "\n", '."
     */
    private String removeSpecialCharacterAtTheEnd(String text) {
        return text.replaceAll("[ \\n.]$", "");
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
            while (inMiddleOfWord(charLimit)) {
                charLimit--;
            }
            return post.substring(0, charLimit);
        } else {
            return post;
        }
    }

    private boolean inMiddleOfWord(int charLimit) {
        char atLimit = post.charAt(charLimit);
        char afterLimit = post.charAt(charLimit + 1);
        return Character.isLetter(atLimit) && Character.isLetter(afterLimit);
    }
}
