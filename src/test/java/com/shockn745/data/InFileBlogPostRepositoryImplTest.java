package com.shockn745.data;

import com.shockn745.domain.application.driven.BlogPostRepository;
import com.shockn745.domain.application.driving.dto.BlogPostDTO;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Kempenich Florian
 */
public class InFileBlogPostRepositoryImplTest {

    private BlogPostRepository repository;

    @BeforeClass
    public static void setUpClass() throws Exception {
        //Write temp file

    }

    @Before
    public void setUp() throws Exception {
        repository = new InFileBlogPostRepositoryImpl();
    }

    @Test
    public void savePost_forNow_doNothing_returnEmptyBlogPost() throws Exception {
        BlogPostDTO blogPost1 = repository.save(BlogPostDTO.make("title", "this is the post text", 324));
        BlogPostDTO blogPost2 = repository.save(null);

        assertEquals(BlogPostDTO.EMPTY, blogPost1);
        assertEquals(BlogPostDTO.EMPTY, blogPost2);
    }

    @Test
    public void getPost_() throws Exception {

    }
}