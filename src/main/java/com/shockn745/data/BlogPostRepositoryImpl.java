package com.shockn745.data;

import com.shockn745.data.jpa.JpaBlogPostRepo;
import com.shockn745.domain.application.driven.BlogPostRepository;
import com.shockn745.domain.application.driving.dto.BlogPostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Simple wrapper for a JPA repository.
 * To not expose the Domain to JPA knowledge.
 *
 * @author Kempenich Florian
 */
@Repository
public class BlogPostRepositoryImpl implements BlogPostRepository{

    private final JpaBlogPostRepo repo;

    @Autowired
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
}
