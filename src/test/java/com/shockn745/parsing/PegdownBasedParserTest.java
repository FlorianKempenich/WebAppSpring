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
        String image = "PICTURE:test.123.456.\"\"\n";
        int postId = 12;
        String expectedHtml = makeImageHTMLTemplate("/assets/blog-post/" + postId + "/test.jpg", 123, 456);

        assertEquals(expectedHtml, parser.toHtml(image, postId));
    }

    @Test
    public void parseImageWithDescription() throws Exception {
        String image = "PICTURE:test.123.456.\"Hey this is a test\"\n";
        int postId = 12;
        String expectedHtml = makeImageHTMLTemplate("/assets/blog-post/" + postId + "/test.jpg", 123, 456, "Hey this is a test");

        assertEquals(expectedHtml, parser.toHtml(image, postId));
    }

    @Test
    public void parseImageWithDescription_specialCharacters() throws Exception {
        String image = "PICTURE:test.123.456.\"Hey this is a: test\"\n";
        int postId = 12;
        String expectedHtml = makeImageHTMLTemplate("/assets/blog-post/" + postId + "/test.jpg", 123, 456, "Hey this is a: test");

        assertEquals(expectedHtml, parser.toHtml(image, postId));
    }

    @Test
    public void parseImage_2DifferentImagesWithSimilarNames() throws Exception {
        String image = "PICTURE:test.124.667.\"\"";
        String image2 = "PICTURE:test_second.999.456.\"\"";
        String toParse = image + "\n\n" + image2 + "\n";
        int postId = 12;
        String expectedHtml = makeImageHTMLTemplate("/assets/blog-post/" + postId + "/test.jpg", 124, 667) + "\n"
                + makeImageHTMLTemplate("/assets/blog-post/" + postId + "/test_second.jpg", 999, 456);

        assertEquals(expectedHtml, parser.toHtml(toParse, postId));
    }

    private String makeImageHTMLTemplate(String imageLink, int width, int height) {
        return makeImageHTMLTemplate(imageLink, width, height, "");
    }
    private String makeImageHTMLTemplate(String imageLink, int width, int height, String description) {
        //language=HTML
        return "<div class=\"row\">\n" +
                "    <div class=\"my-gallery\" itemscope=\"\" itemtype=\"http://schema.org/ImageGallery\">\n" +
                "        <figure itemprop=\"associatedMedia\" itemscope=\"\" itemtype=\"http://schema.org/ImageObject\"\n" +
                "                class=\"col-md-8 col-md-offset-2 gallery-item\">\n" +
                "            <a href=\"" + imageLink + "\" itemprop=\"contentUrl\"\n" +
                "               data-size=\""+ width + "x" + height + "\">\n" +
                "                <img src=\"" + imageLink + "\" itemprop=\"thumbnail\"\n" +
                "                     alt=\"Image description\" class=\"img-rounded img-responsive center-block\"></img>\n" +
                "            </a>\n" +
                "            <figcaption itemprop=\"caption description\" class=\"gallery-caption\">" + description + "\n" +
                "            </figcaption>\n" +
                "        </figure>\n" +
                "    </div>\n" +
                "</div>";
    }
}