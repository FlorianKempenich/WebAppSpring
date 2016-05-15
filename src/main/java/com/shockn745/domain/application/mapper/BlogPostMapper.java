package com.shockn745.domain.application.mapper;

import com.shockn745.domain.application.driving.dto.BlogPostDTO;
import com.shockn745.domain.core.BlogPost;
import com.shockn745.domain.core.BlogPostFactory;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Kempenich Florian
 */
public class BlogPostMapper {

    private BlogPostFactory blogPostFactory;

    public BlogPostMapper(BlogPostFactory blogPostFactory) {
        checkNotNull(blogPostFactory);
        this.blogPostFactory = blogPostFactory;
    }

    public BlogPostDTO transform(BlogPost blogPost) {
        BlogPostDTO result = new BlogPostDTO();
        result.setId(blogPost.id);
        result.setMarkdownPost(blogPost.markdownContent);
        result.setTitle(blogPost.title);
        result.setDate(blogPost.date);
        result.setTags(blogPost.tags);
        return result;
    }

    public BlogPost transform(BlogPostDTO blogPostDTO) {
        return blogPostFactory.make(blogPostDTO.getId(), blogPostDTO.getTitle(), blogPostDTO.getMarkdownPost(), blogPostDTO.getDate(), blogPostDTO.getTags());
    }


    public List<BlogPost> transformListDtoToDomain(List<BlogPostDTO> blogPostDTOs) {
        List<BlogPost> result = new ArrayList<>(blogPostDTOs.size());
        for (BlogPostDTO blogPostDTO : blogPostDTOs) {
            result.add(transform(blogPostDTO));
        }
        return result;
    }

    public List<BlogPostDTO> transformListDomainToDto(List<BlogPost> blogPosts) {
        List<BlogPostDTO> result = new ArrayList<>(blogPosts.size());
        for (BlogPost blogPost : blogPosts) {
            result.add(transform(blogPost));
        }
        return result;
    }
}
