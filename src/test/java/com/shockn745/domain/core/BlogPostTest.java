package com.shockn745.domain.core;

import com.google.common.testing.EqualsTester;
import com.shockn745.domain.application.driven.MarkdownParser;
import com.shockn745.parsing.PegdownBasedParser;
import org.junit.Before;
import org.junit.Test;
import org.pegdown.Extensions;
import org.pegdown.PegDownProcessor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kempenich Florian
 */
public class BlogPostTest {

    private BlogPostFactory blogPostFactory;

    @Before
    public void setUp() throws Exception {

        PegDownProcessor processor = new PegDownProcessor(Extensions.FENCED_CODE_BLOCKS);
        MarkdownParser parser = new PegdownBasedParser(processor);
        blogPostFactory = new BlogPostFactory(parser);
    }

    @Test
    public void testEquality() throws Exception {
        LocalDate date1 = LocalDate.MAX;
        LocalDate date2 = LocalDate.MIN;
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        list1.add("tag1");
        list2.add("tag2");

        new EqualsTester()
                .addEqualityGroup(blogPostFactory.make("title", "hello", date1, list1), blogPostFactory.make("title", "hello", date1, list1))
                .addEqualityGroup(blogPostFactory.make("othertitle", "hello", date1, list1), blogPostFactory.make("othertitle", "hello", date1, list1))
                .addEqualityGroup(blogPostFactory.make("title", "test", date1, list1), blogPostFactory.make("title", "test", date1, list1))
                .addEqualityGroup(blogPostFactory.make("title", "test", date2, list1), blogPostFactory.make("title", "test", date2, list1))
                .addEqualityGroup(blogPostFactory.make("title", "test", date1, list2), blogPostFactory.make("title", "test", date1, list2))
                .testEquals();
    }

}
