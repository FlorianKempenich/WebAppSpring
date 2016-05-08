package com.shockn745.domain.application.driven;

import com.shockn745.domain.application.driving.dto.BlogPostDTO;

import java.util.List;

/**
 * @author Kempenich Florian
 */
public interface BlogPostRepository {

    BlogPostDTO save(BlogPostDTO toSave);
    BlogPostDTO get(int id);

    List<BlogPostDTO> getAll();
}
