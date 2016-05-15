package com.shockn745.domain.application.driving.impl;

import com.shockn745.domain.application.driven.BlogPostRepository;
import com.shockn745.domain.application.driving.BlogPostDetailUseCase;
import com.shockn745.domain.application.driving.dto.BlogPostDTO;
import com.shockn745.domain.application.mapper.BlogPostMapper;
import com.shockn745.domain.core.BlogPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Kempenich Florian
 */
@Component
public class DetailBlogPostUseCaseImpl implements BlogPostDetailUseCase {

    private final BlogPostRepository blogPostRepository;
    private final BlogPostMapper blogPostMapper;

    @Autowired
    public DetailBlogPostUseCaseImpl(BlogPostRepository blogPostRepository, BlogPostMapper blogPostMapper) {
        this.blogPostRepository = blogPostRepository;
        this.blogPostMapper = blogPostMapper;
    }

    @Override
    public BlogPostDTO get(int id) {
        BlogPost blogPost = getBlogPost(id);
        // if needed do stuff with blogPost here
        BlogPostDTO result = blogPostMapper.transform(blogPost);
        result.setId(id);
        return result;
    }

    @Override
    public String getHtml(int postId) {
        BlogPost post = getBlogPost(postId);
        return post.getHtml(postId);
    }

    private BlogPost getBlogPost(int id) {
        BlogPostDTO fromRepository = blogPostRepository.get(id);
        return blogPostMapper.transform(fromRepository);
    }
}
