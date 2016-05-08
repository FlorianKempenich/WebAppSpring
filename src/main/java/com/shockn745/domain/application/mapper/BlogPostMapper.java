package com.shockn745.domain.application.mapper;

import com.shockn745.domain.application.driving.dto.BlogPostDTO;
import com.shockn745.domain.core.BlogPost;
import com.shockn745.domain.core.BlogPostFactory;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Kempenich Florian
 */
@Component
public class BlogPostMapper {

    private BlogPostFactory blogPostFactory;

    public BlogPostMapper(BlogPostFactory blogPostFactory) {
        checkNotNull(blogPostFactory);
        this.blogPostFactory = blogPostFactory;
    }

    public BlogPostDTO transform(BlogPost blogPost) {
        BlogPostDTO result = new BlogPostDTO();
        result.setMarkdownPost(blogPost.markdownContent);
        result.setTitle(blogPost.title);
        return result;
    }

    public BlogPost transform(BlogPostDTO blogPostDTO) {
        return blogPostFactory.make(blogPostDTO.getTitle(), blogPostDTO.getMarkdownPost());
    }
}
