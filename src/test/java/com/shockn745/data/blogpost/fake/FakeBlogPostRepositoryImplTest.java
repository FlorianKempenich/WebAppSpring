package com.shockn745.data.blogpost.fake;

import com.shockn745.domain.application.driven.BlogPostRepository;
import com.shockn745.domain.application.driving.dto.BlogPostDTO;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Kempenich Florian
 */
public class FakeBlogPostRepositoryImplTest {

    private BlogPostRepository repository;

    @Before
    public void setUp() throws Exception {
        repository = new FakeBlogPostRepositoryImpl();
    }

    @Test
    public void save_doNothing_returnEmptyBlogPost() throws Exception {
        BlogPostDTO post1 = repository.save(BlogPostDTO.make("test", "asdfsdafdas", 23L));
        BlogPostDTO post2 = repository.save(null);
        assertEquals(BlogPostDTO.EMPTY, post1);
        assertEquals(BlogPostDTO.EMPTY, post2);
    }

    @Test
    public void getWithAnyId_returnFakePost() throws Exception {
        BlogPostDTO post1 = repository.get(0);
        BlogPostDTO post2 = repository.get(1);
        BlogPostDTO post3 = repository.get(BlogPostDTO.NO_ID);

        assertEquals(FakeBlogPostRepositoryImpl.FAKE_POST, post1);
        assertEquals(FakeBlogPostRepositoryImpl.FAKE_POST, post2);
        assertEquals(FakeBlogPostRepositoryImpl.FAKE_POST, post3);
    }

    @Test
    public void getAll_return3FakePosts() throws Exception {
        List<BlogPostDTO> posts = repository.getAll();

        assertEquals(3, posts.size());
        for (BlogPostDTO post : posts) {
            assertEquals(FakeBlogPostRepositoryImpl.FAKE_POST, post);
        }
    }

}