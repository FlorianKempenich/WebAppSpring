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
                "title1",
                "Hello this is a blog <strong>post</strong> that is quite long. It's about the end of the uni" +
                        "verse, so be prepared if you actually read through. It's going to be " +
                        "long and tough. \n" +
                        "<h3>Ok let's get's started :</h3> At first there was 3 type of stuff, bla bla"
        ));
        expected.add(makePost("title2", "second"));
        expected.add(makePost("title3", "third"));

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
        dto.setPost(text);
        dto.setTitle(title);
        return dto;
    }
}
