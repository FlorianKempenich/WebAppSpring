package com.shockn745.domain.application.driving.impl;

import com.shockn745.domain.application.driven.BlogPostRepository;
import com.shockn745.domain.application.driving.CreateAndSaveNewPostUseCase;
import com.shockn745.domain.application.driving.dto.BlogPostDTO;
import com.shockn745.domain.application.mapper.BlogPostMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Kempenich Florian
 */
public class CreateAndSaveNewPostUseCaseImplTest {

    private CreateAndSaveNewPostUseCase useCase;
    @Mock
    BlogPostRepository blogPostRepository;
    @Captor
    ArgumentCaptor<BlogPostDTO> blogPostCaptor;

    private BlogPostDTO mockSavedPost;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        // Init usecase
        BlogPostMapper mapper = new BlogPostMapper();
        useCase = new CreateAndSaveNewPostUseCaseImpl(blogPostRepository, mapper);

        // Setup mock repo
        mockSavedPost = new BlogPostDTO();
        when(blogPostRepository.save(any(BlogPostDTO.class))).thenReturn(mockSavedPost);
    }

    @Test
    public void exec_returnCorrectPostWithId() throws Exception {
        String post = "Hello this is my first blog post";
        Long id = 23L;
        mockSavedPost.setId(id);
        mockSavedPost.setPost(post);

        BlogPostDTO returned = useCase.execute(post);

        verify(blogPostRepository).save(blogPostCaptor.capture());
        BlogPostDTO saved = blogPostCaptor.getValue();


        // Post saved
        assertEquals(post, saved.getPost());
        assertEquals(post, returned.getPost());

        // Id updated
        assertEquals(id, returned.getId());
    }
}