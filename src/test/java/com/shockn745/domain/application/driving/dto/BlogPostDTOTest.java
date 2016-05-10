package com.shockn745.domain.application.driving.dto;

import com.google.common.testing.EqualsTester;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kempenich Florian
 */
public class BlogPostDTOTest {

    @Test
    public void testEquality() throws Exception {

        LocalDate date1 = LocalDate.MIN;
        LocalDate date2 = LocalDate.MAX;

        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<String> list2bis = new ArrayList<>();
        list1.add("tag1");
        list2.add("tag2");
        list2bis.add("tag2");

        new EqualsTester()
                .addEqualityGroup(
                        BlogPostDTO.make("title", "hello", 4, date1, list1),
                        BlogPostDTO.make("title", "hello", 4, date1, list1)
                )
                .addEqualityGroup(
                        BlogPostDTO.make("othertitle", "hello", 4, date1, list1),
                        BlogPostDTO.make("othertitle", "hello", 4, date1, list1)
                )
                .addEqualityGroup(
                        BlogPostDTO.make("title", "hello", 3, date1, list2),
                        BlogPostDTO.make("title", "hello", 3, date1, list2)
                )
                .addEqualityGroup(
                        BlogPostDTO.make("title", "test", 1, date2, list1),
                        BlogPostDTO.make("title", "test", 1, date2, list1)
                )
                .addEqualityGroup(
                        BlogPostDTO.make("title", "test", 1, date1, list1),
                        BlogPostDTO.make("title", "test", 1, date1, list1)
                )
                .addEqualityGroup(
                        BlogPostDTO.make("title", "test", 1, date2, list2),
                        BlogPostDTO.make("title", "test", 1, date2, list2)
                )
                .addEqualityGroup(
                        BlogPostDTO.make("title", "test", 1, LocalDate.ofEpochDay(3), list2),
                        BlogPostDTO.make("title", "test", 1, LocalDate.ofEpochDay(3), list2)
                )
                .addEqualityGroup(
                        BlogPostDTO.make("title", "test", 1, LocalDate.ofEpochDay(5), list2bis),
                        BlogPostDTO.make("title", "test", 1, LocalDate.ofEpochDay(5), list2bis)
                )
                .addEqualityGroup(
                        BlogPostDTO.make("title", "test", 1, LocalDate.ofEpochDay(5), new ArrayList<>()),
                        BlogPostDTO.make("title", "test", 1, LocalDate.ofEpochDay(5), new ArrayList<>())
                )
                .testEquals();
    }

}