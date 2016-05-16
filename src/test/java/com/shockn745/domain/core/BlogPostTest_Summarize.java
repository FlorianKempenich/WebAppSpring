package com.shockn745.domain.core;

import com.shockn745.domain.DomainTestUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Kempenich Florian
 */
public class BlogPostTest_Summarize {

    private DomainTestUtils domainTestUtils;

    @Before
    public void setUp() throws Exception {
        domainTestUtils = DomainTestUtils.getDefault();
    }

    @Test
    public void summarize_longerPost() throws Exception {
        BlogPost post = domainTestUtils.makeFakeBlogPostFromContent("This is the content");

        int charLimit = 7;
        assertEquals("This is . . .", post.summarize(charLimit));
    }

    @Test
    public void summarize_shorterPost() throws Exception {
        BlogPost post = domainTestUtils.makeFakeBlogPostFromContent("This is the content");

        int charLimit = 90;
        assertEquals("This is the content . . .", post.summarize(charLimit));
    }

    @Test
    public void summarize_sameLengthPost() throws Exception {
        BlogPost post = domainTestUtils.makeFakeBlogPostFromContent("This is");

        int charLimit = 7;
        assertEquals("This is . . .", post.summarize(charLimit));
    }

    @Test
    public void summarize_removeSpecialCharacterBeforeEllipsis() throws Exception {
        BlogPost postDot = domainTestUtils.makeFakeBlogPostFromContent("This is the content.");
        BlogPost postNewLine = domainTestUtils.makeFakeBlogPostFromContent("This is the content\n");
        BlogPost postSpace = domainTestUtils.makeFakeBlogPostFromContent("This is the content ");

        String expected = "This is the content . . .";
        assertEquals(expected, postDot.summarize(1000));
        assertEquals(expected, postNewLine.summarize(1000));
        assertEquals(expected, postSpace.summarize(1000));
    }

    @Test
    public void summarize_doNotCutInTheMiddleOfAWord() throws Exception {
        BlogPost post = domainTestUtils.makeFakeBlogPostFromContent("This is the content, hello this is a test");

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
        BlogPost withTitlePost = domainTestUtils.makeFakeBlogPostFromContent(withTitle);
        BlogPost withoutTitlePost = domainTestUtils.makeFakeBlogPostFromContent(withoutTitle);

        int charLimit = 10000;
        assertEquals("Hello this is a blog markdownContent now we return to the next line\n" +
                "But the text still continues. No problem there . . ." //Stop just before the title
                , withTitlePost.summarize(charLimit)
        );
        assertEquals(withoutTitle + " . . .", withoutTitlePost.summarize(charLimit));
    }

    @Test
    public void removeMarkDownSpecificSymbolsInSummary() throws Exception {
        String withMarkDownMarkup = "Hello this is some text with *bold* and **italic too**, let's remove that!";
        String withoutMarkDownMarkup = "Hello this is some text with bold and italic too, let's remove that!";

        BlogPost withMarkdown = domainTestUtils.makeFakeBlogPostFromContent(withMarkDownMarkup);

        assertEquals(withoutMarkDownMarkup + " . . .", withMarkdown.summarize(100000));
    }

}