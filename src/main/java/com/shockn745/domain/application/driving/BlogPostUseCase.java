package com.shockn745.domain.application.driving;

import com.shockn745.domain.application.driving.dto.BlogPostDTO;

import java.util.List;

/**
 * DEPRECATED - DO NOT USE
 *
 * @author Kempenich Florian
 */
public interface BlogPostUseCase {

    BlogPostDTO save(BlogPostDTO post);

    List<Integer> getAllIds();

}
