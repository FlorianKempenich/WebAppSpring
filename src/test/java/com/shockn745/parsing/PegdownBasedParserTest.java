package com.shockn745.parsing;

import com.shockn745.domain.application.driven.MarkdownParser;
import org.junit.Before;
import org.junit.Test;
import org.pegdown.Extensions;
import org.pegdown.PegDownProcessor;

import static org.junit.Assert.assertEquals;

/**
 * @author Kempenich Florian
 */
public class PegdownBasedParserTest {


    private MarkdownParser parser;

    @Before
    public void setUp() throws Exception {
        PegDownProcessor processor = new PegDownProcessor(Extensions.FENCED_CODE_BLOCKS);
        parser = new PegdownBasedParser(processor);
    }


    @Test
    public void parsePlainText() throws Exception {
        String markdown = "Hello this is the intro\n" +
                "#First title\n";
        String expectedHtml = "<p>Hello this is the intro</p>\n" +
                "<h1>First title</h1>";


        String resultHtml = parser.toHtml(markdown, 0);

        assertEquals(expectedHtml, resultHtml);
    }

    @Test
    public void parseImage() throws Exception {
        String image = "PICTURE:test";
        int postId = 12;
        String expectedHtml = "<div class=\"card-image card-with-shadow\">\n" +
                "    <img src=\"" + "/assets/blog-post/" + postId + "/test.jpg" + "\" alt=\"Rounded Image\" class=\"img-rounded img-responsive\">\n" +
                "</div>";

        assertEquals(expectedHtml, parser.toHtml(image, postId));
    }

    @Test
    public void parseImageWithLink() throws Exception {
        String imageLink = "http://ichef.bbci.co.uk/news/976/media/images/83351000/jpg/_83351965_explorer273lincolnshirewoldssouthpicturebynicholassilkstone.jpg";
        String image = "PICTURE:" + imageLink;
        int postId = 12;
        String expectedHtml = "<div class=\"card-image card-with-shadow\">\n" +
                "    <img src=\"" + imageLink + "\" alt=\"Rounded Image\" class=\"img-rounded img-responsive\">\n" +
                "</div>";

        assertEquals(expectedHtml, parser.toHtml(image, postId));
    }
}