package com.shockn745.domain.application.driving.impl;

import com.shockn745.domain.application.driven.BlogPostRepository;
import com.shockn745.domain.application.driven.MarkdownParser;
import com.shockn745.domain.application.driving.BlogPostUseCase;
import com.shockn745.domain.application.driving.dto.BlogPostDTO;
import com.shockn745.domain.application.mapper.BlogPostMapper;
import com.shockn745.domain.core.BlogPostFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Kempenich Florian
 */
public class BlogPostUseCaseImplTest {

    @Mock
    BlogPostRepository blogPostRepository;
    @Mock
    MarkdownParser parser;
    @Captor
    ArgumentCaptor<BlogPostDTO> blogPostCaptor;
    private BlogPostUseCase useCase;
    private BlogPostDTO mockSavedPost;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        // Init usecase
        BlogPostFactory factory = new BlogPostFactory(parser);
        BlogPostMapper mapper = new BlogPostMapper(factory);
        useCase = new BlogPostUseCaseImpl(blogPostRepository, mapper);

        // Setup mock repo
        mockSavedPost = new BlogPostDTO();
        when(blogPostRepository.save(any(BlogPostDTO.class))).thenReturn(mockSavedPost);
        when(blogPostRepository.get(anyInt())).thenReturn(mockSavedPost);
    }

    @Test
    public void saveNewPost_returnCorrectPostWithId() throws Exception {
        String post = "Hello this is my first blog markdownContent";
        int id = 23;
        mockSavedPost.setId(id);
        mockSavedPost.setMarkdownPost(post);

        BlogPostDTO postDTO = new BlogPostDTO();
        postDTO.setMarkdownPost(post);
        BlogPostDTO returned = useCase.save(postDTO);

        verify(blogPostRepository).save(blogPostCaptor.capture());
        BlogPostDTO saved = blogPostCaptor.getValue();


        // Post saved
        assertEquals(post, saved.getMarkdownPost());
        assertEquals(post, returned.getMarkdownPost());

        // Id updated
        assertEquals(id, returned.getId());
    }

    @Test
    public void getWithId_returnCorrectPost() throws Exception {
        String expectedText = "Expected text";
        int expectedId = 11;
        mockSavedPost.setId(expectedId);
        mockSavedPost.setMarkdownPost(expectedText);

        BlogPostDTO result = useCase.get(expectedId);

        assertEquals(mockSavedPost, result);
    }


    @Test
    public void getAllIds_returnCorrectIdList() throws Exception {
        List<BlogPostDTO> posts = new ArrayList<>(3);
        posts.add(BlogPostDTO.make("Title21", "hello how are you ?", 33, LocalDate.MIN, new ArrayList<>()));
        posts.add(BlogPostDTO.make("sadf", "Second markdownContent", 66, LocalDate.MIN, new ArrayList<>()));
        posts.add(BlogPostDTO.make("test", "asdfl;kjsdaf asd fjasd", 54, LocalDate.MIN, new ArrayList<>()));

        List<Integer> expectedIds = new ArrayList<>(3);
        for (BlogPostDTO post : posts) {
            expectedIds.add(post.getId());
        }

        when(blogPostRepository.getAll()).thenReturn(posts);

        assertEquals(expectedIds, useCase.getAllIds());
    }


    @Test
    public void getHtmlForSpecificPost() throws Exception {
        String markdownText = "markdown post";
        int postId = 12;
        mockSavedPost.setId(postId);
        mockSavedPost.setMarkdownPost(markdownText);

        String expectedHtml = "MOCK HTML";

        when(parser.toHtml(markdownText, postId)).thenReturn(expectedHtml);

        String html = useCase.getHtml(postId);
        assertEquals(expectedHtml, html);
    }
}