package com.shockn745.domain.application.driven;

import com.shockn745.domain.application.driving.dto.BlogPostDTO;

/**
 * @author Kempenich Florian
 */
public interface BlogPostRepository {

    BlogPostDTO save(BlogPostDTO toSave);
    BlogPostDTO get(long id);
}
