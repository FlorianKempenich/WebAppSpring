package com.shockn745.data;

import com.shockn745.domain.application.driven.BlogPostRepository;
import com.shockn745.domain.application.driving.dto.BlogPostDTO;

import java.util.List;

/**
 * @author Kempenich Florian
 */
public class InFileBlogPostRepositoryImpl implements BlogPostRepository {

    @Override
    public BlogPostDTO save(BlogPostDTO toSave) {
        return BlogPostDTO.EMPTY;
    }

    @Override
    public BlogPostDTO get(long id) {
        return null;
    }

    @Override
    public List<BlogPostDTO> getAll() {
        return null;
    }
}
