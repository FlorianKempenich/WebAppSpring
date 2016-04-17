package com.shockn745.domain.application.mapper;

import com.shockn745.domain.application.driving.dto.BlogPostDTO;
import com.shockn745.domain.core.BlogPost;
import org.springframework.stereotype.Component;

/**
 * @author Kempenich Florian
 */
@Component
public class BlogPostMapper {

    public BlogPostDTO transform(BlogPost blogPost) {
        BlogPostDTO result = new BlogPostDTO();
        result.setPost(blogPost.post);
        return result;
    }

    public BlogPost transform(BlogPostDTO blogPostDTO) {
        return new BlogPost(blogPostDTO.getPost());
    }
}
