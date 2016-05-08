package com.shockn745.data.blogpost.fake;

import com.shockn745.domain.application.driven.BlogPostRepository;
import com.shockn745.domain.application.driving.dto.BlogPostDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kempenich Florian
 */
public class FakeBlogPostRepositoryImpl implements BlogPostRepository {

    static final BlogPostDTO FAKE_POST = BlogPostDTO.make(
            "This is a fake blog post",
            "Fake content",
            BlogPostDTO.NO_ID
    );

    @Override
    public BlogPostDTO save(BlogPostDTO toSave) {
        return BlogPostDTO.EMPTY;
    }

    @Override
    public BlogPostDTO get(int id) {
        return FAKE_POST;
    }

    @Override
    public List<BlogPostDTO> getAll() {
        List<BlogPostDTO> posts = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            posts.add(FAKE_POST);
        }
        return posts;
    }
}
