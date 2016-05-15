package com.shockn745.domain.application.driving;

import com.shockn745.domain.application.driving.dto.BlogPostDTO;

/**
 * @author Kempenich Florian
 */
public interface BlogPostDetailUseCase {

    BlogPostDTO get(int id);

    String getHtml(int postId);
}
