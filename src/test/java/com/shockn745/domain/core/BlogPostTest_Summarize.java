package com.shockn745.domain.core;

import com.google.common.testing.EqualsTester;
import com.shockn745.domain.application.driven.MarkdownParser;
import com.shockn745.parsing.PegdownBasedParser;
import org.junit.Before;
import org.junit.Test;
import org.pegdown.Extensions;
import org.pegdown.PegDownProcessor;

import static org.junit.Assert.assertEquals;

/**
 * @author Kempenich Florian
 */
public class BlogPostTest_Summarize {
    
    private BlogPostFactory blogPostFactory;

    @Before
    public void setUp() throws Exception {

        PegDownProcessor processor = new PegDownProcessor(Extensions.FENCED_CODE_BLOCKS);
        MarkdownParser parser = new PegdownBasedParser(processor);
        blogPostFactory = new BlogPostFactory(parser);
    }

    @Test
    public void testEquality() throws Exception {
        new EqualsTester()
                .addEqualityGroup(blogPostFactory.make("title", "hello"), blogPostFactory.make("title", "hello"))
                .addEqualityGroup(blogPostFactory.make("othertitle", "hello"), blogPostFactory.make("othertitle", "hello"))
                .addEqualityGroup(blogPostFactory.make("title", "test"), blogPostFactory.make("title", "test"))
                .testEquals();
    }

    @Test
    public void summarize_longerPost() throws Exception {
        BlogPost post = blogPostFactory.make("This is title", "This is the content");

        int charLimit = 7;
        assertEquals("This is . . .", post.summarize(charLimit));
    }

    @Test
    public void summarize_shorterPost() throws Exception {
        BlogPost post = blogPostFactory.make("This is title", "This is the content");

        int charLimit = 90;
        assertEquals("This is the content . . .", post.summarize(charLimit));
    }

    @Test
    public void summarize_sameLengthPost() throws Exception {
        BlogPost post = blogPostFactory.make("This is title", "This is");

        int charLimit = 7;
        assertEquals("This is . . .", post.summarize(charLimit));
    }

    @Test
    public void summarize_removeSpecialCharacterBeforeEllipsis() throws Exception {
        BlogPost postDot = blogPostFactory.make("Title", "This is the content.");
        BlogPost postNewLine = blogPostFactory.make("Title", "This is the content\n");
        BlogPost postSpace = blogPostFactory.make("Title", "This is the content ");

        String expected = "This is the content . . .";
        assertEquals(expected, postDot.summarize(1000));
        assertEquals(expected, postNewLine.summarize(1000));
        assertEquals(expected, postSpace.summarize(1000));
    }

    @Test
    public void summarize_doNotCutInTheMiddleOfAWord() throws Exception {
        BlogPost post = blogPostFactory.make("Title", "This is the content, hello this is a test");

        int indexInTheMiddleOfWordContent = 15;
        assertEquals("This is the . . .", post.summarize(indexInTheMiddleOfWordContent));
    }

    @Test
    public void summarize_stopAtFirstTitleInPost() throws Exception {
        String withTitle = "Hello this is a blog markdownContent now we return to the next line\n" +
                "But the text still continues. No problem there.\n" +
                "# This is title \n" + // Title in md declared with '#'
                "And text continues";
        String withoutTitle = "Hello this is a blog markdownContent now we return to the next line\n" +
                "But the text still continues. No problem there.\n" +
                "And text continues";
        BlogPost withTitlePost = blogPostFactory.make("This is title", withTitle);
        BlogPost withoutTitlePost = blogPostFactory.make("This is title", withoutTitle);

        int charLimit = 10000;
        assertEquals("Hello this is a blog markdownContent now we return to the next line\n" +
                "But the text still continues. No problem there . . ." //Stop just before the title
                , withTitlePost.summarize(charLimit)
        );
        assertEquals(withoutTitle + " . . .", withoutTitlePost.summarize(charLimit));
    }
}