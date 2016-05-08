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


    MarkdownParser parser;

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


        String resultHtml = parser.toHtml(markdown);

        assertEquals(expectedHtml, resultHtml);
    }
}