package com.shockn745.domain;

import com.shockn745.domain.application.driving.dto.BlogPostDTO;
import com.shockn745.domain.application.mapper.BlogPostMapper;
import com.shockn745.domain.core.BlogPost;
import com.shockn745.domain.core.BlogPostFactory;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.*;

/**
 * @author Kempenich Florian
 */
public class DomainTestUtils {

    private final BlogPostFactory blogPostFactory;
    private final BlogPostMapper blogPostMapper;

    private int counter = 0;

    public DomainTestUtils(BlogPostFactory blogPostFactory, BlogPostMapper blogPostMapper) {
        this.blogPostFactory = blogPostFactory;
        this.blogPostMapper = blogPostMapper;
    }

    public List<BlogPostDTO> makeFakeListPostsDtoWithDecreasingDate(int size) {
        List<BlogPost> fakePosts = makeFakeListPostsWithDecreasingDate(size);
        return blogPostMapper.transformListDomainToDto(fakePosts);
    }

    public List<BlogPost> makeFakeListPostsWithDecreasingDate(int size) {
        List<BlogPost> posts = makeFakeListPostsWithIncreasingDate(size);
        Collections.reverse(posts);
        return posts;
    }

    private List<BlogPost> makeFakeListPostsWithIncreasingDate(int size) {
        List<BlogPost> fakeList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            fakeList.add(makeFakeBlogPost());
        }
        return fakeList;
    }

    public BlogPost makeFakeBlogPost() {
        return makeFakeBlogPostWithTag();
    }

    public BlogPostDTO makeFakeBlogPostDTOWithTag(String... tags) {
        return blogPostMapper.transform(makeFakeBlogPostWithTag(tags));
    }

    public BlogPost makeFakeBlogPostWithTag(String... tags) {
        counter++;
        Random random = new Random();
        int id = counter;
        String content = generateRandomString(random);
        String title = generateRandomString(random);
        LocalDate date = makeDate();
        return blogPostFactory.make(id, title, content, date, Arrays.asList(tags));
    }

    private LocalDate makeDate() {
        int year = 2000 + counter;
        return LocalDate.of(year, 6, 8);
    }

    private String generateRandomString(Random random) {
        return new BigInteger(130, random).toString(32);
    }
}
