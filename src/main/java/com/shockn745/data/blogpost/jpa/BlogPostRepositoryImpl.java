package com.shockn745.data.blogpost.jpa;

import com.shockn745.domain.application.driven.BlogPostRepository;
import com.shockn745.domain.application.driving.dto.BlogPostDTO;

import java.util.List;

/**
 * Simple wrapper for a JPA repository.
 * To not expose the Domain to JPA knowledge.
 *
 * @author Kempenich Florian
 */
public class BlogPostRepositoryImpl implements BlogPostRepository {

    private final JpaBlogPostRepo repo;

    public BlogPostRepositoryImpl(JpaBlogPostRepo repo) {
        this.repo = repo;
    }

    @Override
    public BlogPostDTO save(BlogPostDTO toSave) {
        return repo.save(toSave);
    }

    @Override
    public BlogPostDTO get(long id) {
        return repo.findOne(id);
    }

    @Override
    public List<BlogPostDTO> getAll() {
        return repo.findAll();
    }
}
