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
                .addEqualityGroup(new BlogPost("title", "hello"), new BlogPost("title", "hello"))
                .addEqualityGroup(new BlogPost("othertitle", "hello"), new BlogPost("othertitle", "hello"))
                .addEqualityGroup(new BlogPost("title", "test"), new BlogPost("title", "test"))
                .testEquals();
    }

}