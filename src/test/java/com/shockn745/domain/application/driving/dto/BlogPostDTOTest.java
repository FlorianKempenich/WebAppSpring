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
                .addEqualityGroup(BlogPostDTO.make("hello",4), BlogPostDTO.make("hello",4))
                .addEqualityGroup(BlogPostDTO.make("hello", 3), BlogPostDTO.make("hello", 3))
                .addEqualityGroup(BlogPostDTO.make("test",1), BlogPostDTO.make("test", 1))
                .testEquals();
    }

}