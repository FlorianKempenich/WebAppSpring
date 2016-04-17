package com.shockn745.domain.application.driving.impl;

import com.shockn745.domain.application.driven.BlogPostRepository;
import com.shockn745.domain.application.driving.CreateAndSaveNewPostUseCase;
import com.shockn745.domain.application.driving.dto.BlogPostDTO;
import com.shockn745.domain.application.mapper.BlogPostMapper;
import com.shockn745.domain.core.BlogPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Kempenich Florian
 */
@Component
public class CreateAndSaveNewPostUseCaseImpl implements CreateAndSaveNewPostUseCase {

    private final BlogPostRepository repository;
    private final BlogPostMapper mapper;

    @Autowired
    public CreateAndSaveNewPostUseCaseImpl(BlogPostRepository repository, BlogPostMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public BlogPostDTO execute(String post) {
        BlogPost blogPost = new BlogPost(post);
        BlogPostDTO blogPostDTO = mapper.transform(blogPost);
        blogPostDTO = repository.save(blogPostDTO);
        return blogPostDTO;
    }
}
