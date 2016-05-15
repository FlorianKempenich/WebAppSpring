package com.shockn745.domain.application.driving.impl;

import com.shockn745.domain.DomainTestUtils;
import com.shockn745.domain.application.driven.BlogPostRepository;
import com.shockn745.domain.application.driven.MarkdownParser;
import com.shockn745.domain.application.driving.MainPageUseCase;
import com.shockn745.domain.application.driving.dto.BlogPostDTO;
import com.shockn745.domain.application.mapper.BlogPostMapper;
import com.shockn745.domain.core.BlogPostFactory;
import com.shockn745.domain.core.PagesManagerFactory;
import com.shockn745.parsing.PegdownBasedParser;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.pegdown.PegDownProcessor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * @author Kempenich Florian
 */
public class MainPageUseCaseImplTest {

    private static final int POSTS_PER_PAGE = 3;

    @Mock
    BlogPostRepository blogPostRepository;
    @Mock
    MarkdownParser parser;

    private DomainTestUtils domainTestUtils;

    private MainPageUseCase mainPageUseCase;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        BlogPostFactory blogPostFactory = new BlogPostFactory(parser);
        BlogPostMapper blogPostMapper = new BlogPostMapper(blogPostFactory);
        PagesManagerFactory pagesManagerFactory = new PagesManagerFactory(POSTS_PER_PAGE);

        domainTestUtils = new DomainTestUtils(blogPostFactory, blogPostMapper);

        mainPageUseCase = new MainPageUseCaseImpl(blogPostRepository, pagesManagerFactory, blogPostMapper);
    }

    @Test
    public void getCount() throws Exception {
        List<BlogPostDTO> fakeList = domainTestUtils.makeFakeListPostsDtoWithDecreasingDate(6);
        when(blogPostRepository.getAll()).thenReturn(fakeList);

        assertEquals(2, mainPageUseCase.getPageCount());
    }

    @Test
    public void getSpecificPage() throws Exception {
        List<BlogPostDTO> fakeList = domainTestUtils.makeFakeListPostsDtoWithDecreasingDate(6);
        when(blogPostRepository.getAll()).thenReturn(fakeList);


        List<BlogPostDTO> firstPage = fakeList.subList(0, 3);
        assertEquals(firstPage, mainPageUseCase.getPage(0));
    }

    @Test
    public void checkThat_id_correspondTo_idFromRepository() throws Exception {
        int expectedId = 12;
        BlogPostDTO postDTO = BlogPostDTO.EMPTY;
        postDTO.setId(expectedId);

        List<BlogPostDTO> fakeList = new ArrayList<>();
        fakeList.add(postDTO);
        when(blogPostRepository.getAll()).thenReturn(fakeList);

        List<BlogPostDTO> firstPageResult = mainPageUseCase.getPage(0);
        assertEquals(expectedId, firstPageResult.get(0).getId());
    }

    @Test
    public void getSummaryForSpecificPost() throws Exception {
        String postText = "Expected text that is a bit long, and will be shortened";
        int postId = 11;
        BlogPostDTO postDTO = BlogPostDTO.EMPTY;
        postDTO.setId(postId);
        postDTO.setMarkdownPost(postText);
        when(blogPostRepository.get(anyInt())).thenReturn(postDTO);

        String summary = mainPageUseCase.getSummary(postId);
        String summaryWithoutEllipsis = summary.substring(0, summary.length() - 6);
        assertTrue(postText.startsWith(summaryWithoutEllipsis));
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

        String html = mainPageUseCase.getHtml(postId);
        assertEquals(expectedHtml, html);
    }
}