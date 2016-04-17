package com.shockn745.domain.application.driving.dto;

import com.google.common.testing.EqualsTester;
import org.junit.Test;

/**
 * @author Kempenich Florian
 */
public class BlogPostDTOTest {

    @Test
    public void testEquality() throws Exception {
        new EqualsTester()
                .addEqualityGroup(BlogPostDTO.make("title", "hello", 4), BlogPostDTO.make("title", "hello", 4))
                .addEqualityGroup(BlogPostDTO.make("othertitle", "hello", 4), BlogPostDTO.make("othertitle", "hello", 4))
                .addEqualityGroup(BlogPostDTO.make("title", "hello", 3), BlogPostDTO.make("title", "hello", 3))
                .addEqualityGroup(BlogPostDTO.make("title", "test", 1), BlogPostDTO.make("title", "test", 1))
                .testEquals();
    }

}