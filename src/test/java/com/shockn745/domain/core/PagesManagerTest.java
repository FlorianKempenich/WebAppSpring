package com.shockn745.domain.core;

import com.shockn745.domain.DomainTestUtils;
import com.shockn745.domain.application.driven.MarkdownParser;
import com.shockn745.parsing.PegdownBasedParser;
import org.junit.Before;
import org.junit.Test;
import org.pegdown.Extensions;
import org.pegdown.PegDownProcessor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Kempenich Florian
 */
public class PagesManagerTest {

    private BlogPostFactory blogPostFactory;
    private PagesManager pagesManager;

    @Before
    public void setUp() throws Exception {
        PegDownProcessor processor = new PegDownProcessor(Extensions.FENCED_CODE_BLOCKS);
        MarkdownParser markdownParser = new PegdownBasedParser(processor);
        blogPostFactory = new BlogPostFactory(markdownParser);
    }

    @Test
    public void emptyList_1pages_empty() throws Exception {
        List<BlogPost> emptyList = new ArrayList<>();
        pagesManager = new PagesManager(emptyList, 5);

        assertEquals(1, pagesManager.getPagesCount());
        assertTrue(pagesManager.getPage(0).isEmpty());
    }

    @Test
    public void lessPostsThanPerPage_onePage() throws Exception {
        List<BlogPost> threePosts = DomainTestUtils.makeFakeListPosts(3, blogPostFactory);
        pagesManager = new PagesManager(threePosts, 10);

        assertEquals(1, pagesManager.getPagesCount());
        assertEquals(threePosts, pagesManager.getPage(0));
    }

    @Test(expected = NullPointerException.class)
    public void doNotAcceptNullList() throws Exception {
        pagesManager = new PagesManager(null, 11);
    }

    @Test
    public void doNotAcceptNegativeOrZeroPostsPerPage() throws Exception {
        try {
            pagesManager = new PagesManager(new ArrayList<>(), 0);
            fail("Should throw exception");
        } catch (IllegalStateException e) {
            // do nothing, expected
        }

        try {
            pagesManager = new PagesManager(new ArrayList<>(), -1);
            fail("Should throw exception");
        } catch (IllegalStateException e) {
            //expected
        }
    }


    @Test
    public void SixPosts_twoPerPage_moduloZero() throws Exception {
        List<BlogPost> sixPosts = DomainTestUtils.makeFakeListPosts(6, blogPostFactory);

        List<BlogPost> expectedFirstPage = sixPosts.subList(0, 2);
        List<BlogPost> expectedSecondPage = sixPosts.subList(2, 4);
        List<BlogPost> expectedThirdPage = sixPosts.subList(4, 6);

        pagesManager = new PagesManager(sixPosts, 2);

        assertEquals(3, pagesManager.getPagesCount());
        assertEquals(expectedFirstPage, pagesManager.getPage(0));
        assertEquals(expectedSecondPage, pagesManager.getPage(1));
        assertEquals(expectedThirdPage, pagesManager.getPage(2));
    }

    @Test
    public void SevenPosts_threePerPage_moduloNotZero() throws Exception {
        List<BlogPost> sevenPosts = DomainTestUtils.makeFakeListPosts(7, blogPostFactory);

        List<BlogPost> expectedFirstPage = sevenPosts.subList(0, 3);
        List<BlogPost> expectedSecondPage = sevenPosts.subList(3, 6);
        List<BlogPost> expectedThirdPage = sevenPosts.subList(6, 7); // Page with single post

        pagesManager = new PagesManager(sevenPosts, 3);

        assertEquals(3, pagesManager.getPagesCount());
        assertEquals(expectedFirstPage, pagesManager.getPage(0));
        assertEquals(expectedSecondPage, pagesManager.getPage(1));
        assertEquals(expectedThirdPage, pagesManager.getPage(2));
    }


}