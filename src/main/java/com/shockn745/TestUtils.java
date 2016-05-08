package com.shockn745;

import com.shockn745.data.blogpost.jpa.JpaBlogPostRepo;
import com.shockn745.domain.application.driving.dto.BlogPostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kempenich Florian
 */
@Component
public class TestUtils {

    @Autowired
    JpaBlogPostRepo jpaRepo;

    public List<BlogPostDTO> fillDatabaseWithTestData() {
        List<BlogPostDTO> expected = new ArrayList<>(3);
        expected.add(makePost(
                "Placeholder markdownContent",
                "Hello this is a blog <strong>post</strong> that is quite long. It's about the end of the uni" +
                        "verse, so be prepared if you actually read through. It's going to be " +
                        "long and tough. \n" +
                        "##Ok let's get's started : At first there was 3 type of stuff, bla bla"
        ));
        expected.add(makePost("Fake", "Hello this is the second blog post of the series. It is basically nothing else" +
                " than just, fake text to fill the blanks.\n" +
                "I hope it's not too boring ^_^. Well let's see. At least we have some data to show the design of the " +
                "website.\n" +
                "I'm happy this project is soon to be done :)"));
        expected.add(makePost("Lorem Ipsum", "Contrary to popular belief, Lorem Ipsum is not simply random text. It has" +
                " roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard " +
                "McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure" +
                " Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in" +
                " classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and " +
                "1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC." +
                " This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line " +
                "of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32."));

        for (BlogPostDTO postDTO : expected) {
            BlogPostDTO saved = jpaRepo.save(postDTO);
            postDTO.setId(saved.getId());
        }
        return expected;
    }

    public void eraseDatabase() {
        jpaRepo.deleteAll();
    }

    public static BlogPostDTO makePost(String title, String text) {
        BlogPostDTO dto = new BlogPostDTO();
        dto.setMarkdownPost(text);
        dto.setTitle(title);
        return dto;
    }
}
