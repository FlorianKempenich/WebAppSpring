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
import static org.mockito.Mockito.when;

/**
 * @author Kempenich Florian
 */
public class MainPageUseCaseImplTest {

    private static final int POSTS_PER_PAGE = 3;

    @Mock
    BlogPostRepository blogPostRepository;

    private DomainTestUtils domainTestUtils;

    private MainPageUseCase mainPageUseCase;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        MarkdownParser parser = new PegdownBasedParser(new PegDownProcessor());
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


}