package com.shockn745.domain.core;

import com.google.common.testing.EqualsTester;
import org.junit.Test;

/**
 * @author Kempenich Florian
 */
public class BlogPostTest {

    @Test
    public void testEquality() throws Exception {
        new EqualsTester()
                .addEqualityGroup(new BlogPost("hello"), new BlogPost("hello"))
                .addEqualityGroup(new BlogPost("test"), new BlogPost("test"))
                .testEquals();
    }

}