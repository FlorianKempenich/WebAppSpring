package com.shockn745.domain;

import com.shockn745.domain.application.driving.dto.BlogPostDTO;
import com.shockn745.domain.application.mapper.BlogPostMapper;
import com.shockn745.domain.core.BlogPost;
import com.shockn745.domain.core.BlogPostFactory;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Kempenich Florian
 */
public class DomainTestUtils {

    public static List<BlogPostDTO> makeFakeListPostsDto(int size, BlogPostFactory blogPostFactory, BlogPostMapper mapper) {
        List<BlogPost> fakePosts = makeFakeListPosts(size, blogPostFactory);
        return mapper.transformListDomainToDto(fakePosts);
    }

    public static List<BlogPost> makeFakeListPosts(int size, BlogPostFactory blogPostFactory) {
        List<BlogPost> fakeList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            fakeList.add(makeFakeBlogPost(blogPostFactory));
        }
        return fakeList;
    }

    private static BlogPost makeFakeBlogPost(BlogPostFactory blogPostFactory) {
        Random random = new Random();
        String content = generateRandomString(random);
        String title = generateRandomString(random);
        return blogPostFactory.make(title, content, LocalDate.MIN, new ArrayList<>());
    }

    private static String generateRandomString(Random random) {
        return new BigInteger(130, random).toString(32);
    }
}
