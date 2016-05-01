package com.shockn745.domain.core;

import com.google.common.testing.EqualsTester;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

    @Test
    public void summarize_longerPost() throws Exception {
        BlogPost post = new BlogPost("This is title", "This is the content");

        int charLimit = 7;
        assertEquals("This is", post.summarize(charLimit));
    }

    @Test
    public void summarize_shorterPost() throws Exception {
        BlogPost post = new BlogPost("This is title", "This is the content");

        int charLimit = 90;
        assertEquals("This is the content", post.summarize(charLimit));
    }

    @Test
    public void summarize_sameLengthPost() throws Exception {
        BlogPost post = new BlogPost("This is title", "This is");

        int charLimit = 7;
        assertEquals("This is", post.summarize(charLimit));
    }

    @Test
    public void summarize_stopAtFirstTitleInPost() throws Exception {
        String withTitle = "Hello this is a blog post now we return to the next line\n" +
                "But the text still continues. No problem there.\n" +
                "# This is title \n" + // Title in md declared with '#'
                "And text continues";
        String withoutTitle = "Hello this is a blog post now we return to the next line\n" +
                "But the text still continues. No problem there.\n" +
                "And text continues";
        BlogPost withTitlePost = new BlogPost("This is title", withTitle);
        BlogPost withoutTitlePost = new BlogPost("This is title", withoutTitle);

        int charLimit = 10000;
        assertEquals("Hello this is a blog post now we return to the next line\n" +
                "But the text still continues. No problem there.\n" //Stop just before the title
                , withTitlePost.summarize(charLimit)
        );
        assertEquals(withoutTitle, withoutTitlePost.summarize(charLimit));
    }
}