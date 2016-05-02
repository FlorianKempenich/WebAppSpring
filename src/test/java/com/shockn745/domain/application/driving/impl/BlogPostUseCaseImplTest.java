package com.shockn745.domain.application.driving.impl;

import com.shockn745.domain.application.driven.BlogPostRepository;
import com.shockn745.domain.application.driving.BlogPostUseCase;
import com.shockn745.domain.application.driving.dto.BlogPostDTO;
import com.shockn745.domain.application.mapper.BlogPostMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Kempenich Florian
 */
public class BlogPostUseCaseImplTest {

    @Mock
    BlogPostRepository blogPostRepository;
    @Captor
    ArgumentCaptor<BlogPostDTO> blogPostCaptor;
    private BlogPostUseCase useCase;
    private BlogPostDTO mockSavedPost;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        // Init usecase
        BlogPostMapper mapper = new BlogPostMapper();
        useCase = new BlogPostUseCaseImpl(blogPostRepository, mapper);

        // Setup mock repo
        mockSavedPost = new BlogPostDTO();
        when(blogPostRepository.save(any(BlogPostDTO.class))).thenReturn(mockSavedPost);
        when(blogPostRepository.get(anyLong())).thenReturn(mockSavedPost);
    }

    @Test
    public void saveNewPost_returnCorrectPostWithId() throws Exception {
        String post = "Hello this is my first blog post";
        Long id = 23L;
        mockSavedPost.setId(id);
        mockSavedPost.setPost(post);

        BlogPostDTO postDTO = new BlogPostDTO();
        postDTO.setPost(post);
        BlogPostDTO returned = useCase.save(postDTO);

        verify(blogPostRepository).save(blogPostCaptor.capture());
        BlogPostDTO saved = blogPostCaptor.getValue();


        // Post saved
        assertEquals(post, saved.getPost());
        assertEquals(post, returned.getPost());

        // Id updated
        assertEquals(id, returned.getId());
    }

    @Test
    public void getWithId_returnCorrectPost() throws Exception {
        String expectedText = "Expected text";
        Long expectedId = 11L;
        mockSavedPost.setId(expectedId);
        mockSavedPost.setPost(expectedText);

        BlogPostDTO result = useCase.get(expectedId);

        assertEquals(mockSavedPost, result);
    }


    @Test
    public void getAllIds_returnCorrectIdList() throws Exception {
        List<BlogPostDTO> posts = new ArrayList<>(3);
        posts.add(BlogPostDTO.make("Title21", "hello how are you ?", 33L));
        posts.add(BlogPostDTO.make("sadf", "Second post", 66L));
        posts.add(BlogPostDTO.make("test", "asdfl;kjsdaf asd fjasd", 54L));

        List<Long> expectedIds = new ArrayList<>(3);
        for (BlogPostDTO post : posts) {
            expectedIds.add(post.getId());
        }

        when(blogPostRepository.getAll()).thenReturn(posts);

        assertEquals(expectedIds, useCase.getAllIds());
    }

    @Test
    public void getSummaryForSpecificPost() throws Exception {
        String postText = "Expected text that is a bit long, and will be shortened";
        Long postId = 11L;
        mockSavedPost.setId(postId);
        mockSavedPost.setPost(postText);

        String summary = useCase.getSummary(postId);
        String summaryWithoutEllipsis = summary.substring(0, summary.length() - 6);
        assertTrue(postText.startsWith(summaryWithoutEllipsis));
    }
}