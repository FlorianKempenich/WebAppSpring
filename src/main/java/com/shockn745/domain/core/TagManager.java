package com.shockn745.domain.core;

import com.shockn745.domain.application.driven.BlogPostRepository;
import com.shockn745.domain.application.driving.dto.BlogPostDTO;
import com.shockn745.domain.application.mapper.BlogPostMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kempenich Florian
 */
public class TagManager {

    private final BlogPostRepository blogPostRepository;
    private final BlogPostMapper blogPostMapper;

    public TagManager(BlogPostRepository blogPostRepository, BlogPostMapper blogPostMapper) {
        this.blogPostRepository = blogPostRepository;
        this.blogPostMapper = blogPostMapper;
    }

    public List<BlogPost> findPosts(String tag) {
        List<BlogPostDTO> allPosts = blogPostRepository.getAll();

        List<BlogPost> result = new ArrayList<>();
        for (BlogPostDTO post : allPosts) {
            if (isMatch(tag, post)) {
                result.add(blogPostMapper.transform(post));
            }
        }

        return result;
    }

    private boolean isMatch(String tag, BlogPostDTO post) {
        List<String> postTags = post.getTags();

        for (String postTag : postTags) {
            if (isTagMatch(tag, postTag)) {
                return true;
            }
        }

        return false;
    }

    private boolean isTagMatch(String tag, String postTag) {
        return postTag.trim().equalsIgnoreCase(tag);
    }
}
