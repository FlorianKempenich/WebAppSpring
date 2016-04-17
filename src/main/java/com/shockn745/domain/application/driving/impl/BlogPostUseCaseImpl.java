package com.shockn745.domain.application.driving.impl;

import com.shockn745.domain.application.driven.BlogPostRepository;
import com.shockn745.domain.application.driving.BlogPostUseCase;
import com.shockn745.domain.application.driving.dto.BlogPostDTO;
import com.shockn745.domain.application.mapper.BlogPostMapper;
import com.shockn745.domain.core.BlogPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Kempenich Florian
 */
@Component
public class BlogPostUseCaseImpl implements BlogPostUseCase {

    private final BlogPostRepository repository;
    private final BlogPostMapper mapper;

    @Autowired
    public BlogPostUseCaseImpl(BlogPostRepository repository, BlogPostMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public BlogPostDTO save(BlogPostDTO post) {
        BlogPost blogPost = mapper.transform(post);
        BlogPostDTO blogPostDTO = mapper.transform(blogPost);
        // if needed do stuff with blogPost here
        blogPostDTO = repository.save(blogPostDTO);
        return blogPostDTO;
    }

    @Override
    public BlogPostDTO get(long id) {
        BlogPostDTO fromRepository = repository.get(id);
        BlogPost blogPost = mapper.transform(fromRepository);
        // if needed do stuff with blogPost here
        BlogPostDTO result = mapper.transform(blogPost);
        result.setId(id);
        return result;
    }

    @Override
    public List<BlogPostDTO> getAll() {
        return repository.getAll();
    }
}
