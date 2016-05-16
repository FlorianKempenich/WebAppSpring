package com.shockn745.domain.core;

import com.shockn745.domain.DomainTestUtils;
import com.shockn745.domain.application.driven.BlogPostRepository;
import com.shockn745.domain.application.driven.MarkdownParser;
import com.shockn745.domain.application.driving.dto.BlogPostDTO;
import com.shockn745.domain.application.mapper.BlogPostMapper;
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
public class TagManagerTest {

    @Mock
    BlogPostRepository blogPostRepository;
    @Mock
    MarkdownParser parser;
    private TagManager tagManager;
    private BlogPostMapper mapper;
    private DomainTestUtils domainTestUtils;

    private BlogPostDTO withHelloTag;
    private BlogPostDTO withHelloTagCaps;
    private BlogPostDTO withHelloTagMixedCase;
    private BlogPostDTO withHelloTagWithSpaces;

    private BlogPostDTO withWorldTag1;
    private BlogPostDTO withWorldTag2;

    private BlogPostDTO withOtherTag;
    private BlogPostDTO withTagTag;
    private BlogPostDTO withMixedTag;
    private BlogPostDTO withTestTag;
    private BlogPostDTO withTwoDifferentTags;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        BlogPostFactory factory = new BlogPostFactory(parser);
        mapper = new BlogPostMapper(factory);
        domainTestUtils = new DomainTestUtils(factory, mapper);

        tagManager = new TagManager(blogPostRepository, mapper);

        initPostsInRepository();
    }

    private void initPostsInRepository() {
        List<BlogPostDTO> postsInRepository = new ArrayList<>();

        withHelloTag = domainTestUtils.makeFakeBlogPostDTOWithTag("hello");
        withHelloTagCaps = domainTestUtils.makeFakeBlogPostDTOWithTag("HELLO");
        withHelloTagMixedCase = domainTestUtils.makeFakeBlogPostDTOWithTag("hEllO");
        withHelloTagWithSpaces = domainTestUtils.makeFakeBlogPostDTOWithTag("  hello     ");

        withWorldTag1 = domainTestUtils.makeFakeBlogPostDTOWithTag("world");
        withWorldTag2 = domainTestUtils.makeFakeBlogPostDTOWithTag("world");

        withOtherTag = domainTestUtils.makeFakeBlogPostDTOWithTag("other");
        withTagTag = domainTestUtils.makeFakeBlogPostDTOWithTag("tag");
        withMixedTag = domainTestUtils.makeFakeBlogPostDTOWithTag("mixed");
        withTestTag = domainTestUtils.makeFakeBlogPostDTOWithTag("test");
        withTwoDifferentTags = domainTestUtils.makeFakeBlogPostDTOWithTag("two", "different");


        postsInRepository.add(withHelloTag);
        postsInRepository.add(withHelloTagCaps);
        postsInRepository.add(withHelloTagMixedCase);
        postsInRepository.add(withHelloTagWithSpaces);

        postsInRepository.add(withWorldTag1);
        postsInRepository.add(withWorldTag2);

        postsInRepository.add(withOtherTag);
        postsInRepository.add(withTagTag);
        postsInRepository.add(withMixedTag);
        postsInRepository.add(withTestTag);
        postsInRepository.add(withTwoDifferentTags);

        when(blogPostRepository.getAll()).thenReturn(postsInRepository);
    }

    @Test
    public void nullTag_returnEmptyList() throws Exception {
        List<BlogPost> postsContainingTags = tagManager.findPosts(null);
        assertTrue(postsContainingTags.isEmpty());
    }

    @Test
    public void noCorrespondingPosts_returnEmptyList() throws Exception {
        List<BlogPost> postsContainingTags = tagManager.findPosts("no-corresponding-tag");
        assertTrue(postsContainingTags.isEmpty());
    }

    @Test
    public void exactMatch_returnList() throws Exception {
        List<BlogPost> expected = makeExpectedList(withWorldTag1, withWorldTag2);
        List<BlogPost> result = tagManager.findPosts("world");

        assertEquals(expected, result);
    }

    private List<BlogPost> makeExpectedList(BlogPostDTO... posts) {
        List<BlogPost> expected = new ArrayList<>(posts.length);
        for (BlogPostDTO post : posts) {
            expected.add(mapper.transform(post));
        }
        return expected;
    }

    @Test
    public void mixedCase_containsMatch() throws Exception {
        List<BlogPost> expectedToBeIncluded = makeExpectedList(withHelloTag, withHelloTagCaps, withHelloTagMixedCase);
        List<BlogPost> result = tagManager.findPosts("hello");

        assertTrue("Should contain", result.containsAll(expectedToBeIncluded));
    }

    @Test
    public void ignoreLeadingAndTrailingSpaces_containsMatch() throws Exception {
        List<BlogPost> expectedToBeIncluded = makeExpectedList(withHelloTagWithSpaces);
        List<BlogPost> result = tagManager.findPosts("hello");

        assertTrue("Should contain", result.containsAll(expectedToBeIncluded));
    }


    @Test
    public void noPosts_noPopularTags_emptyList() throws Exception {
        when(blogPostRepository.getAll()).thenReturn(new ArrayList<>());

        List<String> popularTags = tagManager.getPopularTags(10);
        assertTrue(popularTags.isEmpty());
    }

    @Test
    public void fewerTagsThanRequested_samePopularity_returnAllTags_withoutDuplicate_noOrder() throws Exception {
        List<BlogPostDTO> postsWithTags = new ArrayList<>();
        postsWithTags.add(withHelloTag);
        postsWithTags.add(withHelloTag);
        postsWithTags.add(withTwoDifferentTags);
        when(blogPostRepository.getAll()).thenReturn(postsWithTags);

        List<String> expected = new ArrayList<>();
        expected.add("hello");
        expected.add("two");
        expected.add("different");

        List<String> result = tagManager.getPopularTags(10);
        assertTrue(result.containsAll(expected));
        assertTrue(expected.containsAll(result));
    }

    @Test
    public void mixedCaseAndSpaces_fewerTagsThanRequested_samePopularity_returnAllTags_withoutDuplicate_noOrder() throws Exception {
        List<BlogPostDTO> postsWithTags = new ArrayList<>();
        postsWithTags.add(withHelloTag);
        postsWithTags.add(withHelloTagCaps);
        postsWithTags.add(withHelloTagMixedCase);
        postsWithTags.add(withHelloTagWithSpaces);
        postsWithTags.add(withTwoDifferentTags);
        when(blogPostRepository.getAll()).thenReturn(postsWithTags);

        List<String> expected = new ArrayList<>();
        expected.add("hello");
        expected.add("two");
        expected.add("different");

        List<String> result = tagManager.getPopularTags(10);
        assertTrue(result.containsAll(expected));
        assertTrue(expected.containsAll(result));
    }


    @Test
    public void orderByPopularity() throws Exception {
        List<BlogPostDTO> postsWithTags = new ArrayList<>();
        postsWithTags.add(withHelloTag);
        postsWithTags.add(withHelloTag);
        postsWithTags.add(withHelloTag);

        postsWithTags.add(withOtherTag);

        postsWithTags.add(withWorldTag1);
        postsWithTags.add(withWorldTag1);

        when(blogPostRepository.getAll()).thenReturn(postsWithTags);

        List<String> expected = new ArrayList<>();
        expected.add("hello");
        expected.add("world");
        expected.add("other");

        assertEquals(expected, tagManager.getPopularTags(10));
    }

    @Test
    public void limitNumberOfTags() throws Exception {
        List<BlogPostDTO> postsWithTags = new ArrayList<>();
        postsWithTags.add(withHelloTag);
        postsWithTags.add(withHelloTag);
        postsWithTags.add(withHelloTag);

        postsWithTags.add(withOtherTag);

        postsWithTags.add(withWorldTag1);
        postsWithTags.add(withWorldTag1);

        when(blogPostRepository.getAll()).thenReturn(postsWithTags);

        List<String> expected = new ArrayList<>();
        expected.add("hello");
        expected.add("world");

        assertEquals(expected, tagManager.getPopularTags(2));
    }
}