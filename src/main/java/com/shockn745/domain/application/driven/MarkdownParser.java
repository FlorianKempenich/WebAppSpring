package com.shockn745.domain.application.driven;

/**
 * @author Kempenich Florian
 */
public interface MarkdownParser {

    String toHtml(String markdown, int postId);

}
