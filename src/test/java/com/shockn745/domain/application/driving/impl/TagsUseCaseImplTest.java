package com.shockn745.domain.application.driving.impl;

import com.shockn745.domain.DomainTestUtils;
import com.shockn745.domain.application.driving.TagsUseCase;
import com.shockn745.domain.core.BlogPost;
import com.shockn745.domain.core.PagesManagerFactory;
import com.shockn745.domain.core.TagManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * @author Kempenich Florian
 */
public class TagsUseCaseImplTest {

    private static final int THREE_POSTS_PER_PAGE = 3;
    private static final String TAG = "TAG";
    @Mock
    TagManager tagManager;
    private TagsUseCase tagsUseCase;
    private DomainTestUtils domainTestUtils;

    private List<BlogPost> sevenPostsWithTag;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        domainTestUtils = DomainTestUtils.getDefault();
        initListOfSevenPostWithTag();
        PagesManagerFactory pagesManagerFactory = new PagesManagerFactory(THREE_POSTS_PER_PAGE);

        tagsUseCase = new TagsUseCaseImpl(tagManager, pagesManagerFactory);
    }

    private void initListOfSevenPostWithTag() {
        sevenPostsWithTag = new ArrayList<>();
        sevenPostsWithTag.add(domainTestUtils.makeFakeBlogPostWithTag(TAG));
        sevenPostsWithTag.add(domainTestUtils.makeFakeBlogPostWithTag(TAG));
        sevenPostsWithTag.add(domainTestUtils.makeFakeBlogPostWithTag(TAG));
        sevenPostsWithTag.add(domainTestUtils.makeFakeBlogPostWithTag(TAG));
        sevenPostsWithTag.add(domainTestUtils.makeFakeBlogPostWithTag(TAG));
        sevenPostsWithTag.add(domainTestUtils.makeFakeBlogPostWithTag(TAG));
        sevenPostsWithTag.add(domainTestUtils.makeFakeBlogPostWithTag(TAG));
        assert sevenPostsWithTag.size() == 7;
        when(tagManager.findPosts("TAG")).thenReturn(sevenPostsWithTag);
    }

    @Test
    public void getPageCountWithTag_returnThree() throws Exception {
        int pageCountForTag = tagsUseCase.getPageCount(TAG);
        assertEquals(3, pageCountForTag);
    }

    @Test
    public void getFirstPage() throws Exception {
        List<BlogPost> firstPage = tagsUseCase.getPage(TAG, 0);

        assertEquals(3, firstPage.size());
        assertTrue(sevenPostsWithTag.containsAll(firstPage));
    }


}