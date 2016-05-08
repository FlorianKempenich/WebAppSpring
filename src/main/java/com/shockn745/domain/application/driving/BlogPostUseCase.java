package com.shockn745.domain.application.driving;

import com.shockn745.domain.application.driving.dto.BlogPostDTO;

import java.util.List;

/**
 * @author Kempenich Florian
 */
public interface BlogPostUseCase {

    BlogPostDTO save(BlogPostDTO post);

    BlogPostDTO get(int id);

    List<Integer> getAllIds();

    String getSummary(int postId);
}
