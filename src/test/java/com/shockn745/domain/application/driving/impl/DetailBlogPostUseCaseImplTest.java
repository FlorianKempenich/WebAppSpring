package com.shockn745.domain.application.driving.impl;

import com.shockn745.domain.application.driven.BlogPostRepository;
import com.shockn745.domain.application.driven.MarkdownParser;
import com.shockn745.domain.application.driving.BlogPostDetailUseCase;
import com.shockn745.domain.application.driving.dto.BlogPostDTO;
import com.shockn745.domain.application.mapper.BlogPostMapper;
import com.shockn745.domain.core.BlogPostFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * @author Kempenich Florian
 */
public class DetailBlogPostUseCaseImplTest {

    @Mock
    BlogPostRepository blogPostRepository;
    @Mock
    MarkdownParser parser;

    private BlogPostDetailUseCase blogPostDetailUseCase;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        BlogPostFactory blogPostFactory = new BlogPostFactory(parser);
        BlogPostMapper blogPostMapper = new BlogPostMapper(blogPostFactory);


        blogPostDetailUseCase = new DetailBlogPostUseCaseImpl(blogPostRepository, blogPostMapper);
    }

    @Test
    public void getWithId_returnCorrectPost() throws Exception {
        String postText = "Expected text that is a bit long, and will be shortened";
        int postId = 14;
        BlogPostDTO postDTO = BlogPostDTO.EMPTY;
        postDTO.setId(postId);
        postDTO.setMarkdownPost(postText);
        when(blogPostRepository.get(anyInt())).thenReturn(postDTO);

        BlogPostDTO result = blogPostDetailUseCase.get(postId);

        assertEquals(postDTO, result);
    }

    @Test
    public void getHtmlForSpecificPost() throws Exception {
        String postText = "Expected text that is a bit long, and will be shortened";
        int postId = 14;
        BlogPostDTO postDTO = BlogPostDTO.EMPTY;
        postDTO.setId(postId);
        postDTO.setMarkdownPost(postText);
        when(blogPostRepository.get(anyInt())).thenReturn(postDTO);

        String expectedHtml = "MOCK HTML";

        when(parser.toHtml(postText, postId)).thenReturn(expectedHtml);

        String html = blogPostDetailUseCase.getHtml(postId);
        assertEquals(expectedHtml, html);
    }
}