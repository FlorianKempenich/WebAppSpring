package com.shockn745.data.blogpost.jpa;

import com.shockn745.domain.application.driving.dto.BlogPostDTO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Kempenich Florian
 */
public interface JpaBlogPostRepo extends JpaRepository<BlogPostDTO, Long> {
}
