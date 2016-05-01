package com.shockn745.integrationtest;

import com.shockn745.TestUtils;
import com.shockn745.WebAppApplication;
import com.shockn745.domain.application.driving.BlogPostUseCase;
import com.shockn745.domain.application.driving.dto.BlogPostDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Kempenich Florian
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebAppApplication.class)
@WebAppConfiguration
@ActiveProfiles("integration-test")
public class BlogPostIntegrationTest {


    @Autowired
    BlogPostUseCase useCase;
    @Autowired
    TestUtils testUtils;

    private String postText = "Hello this is blog postText";

    @Before
    public void setUp() throws Exception {
        testUtils.eraseDatabase();
    }

    @Test
    public void saveAndRetrieve_samePost() throws Exception {
        BlogPostDTO toSave = TestUtils.makePost("title", postText);
        BlogPostDTO blogPostDTO = useCase.save(toSave);
        long id = blogPostDTO.getId();
        toSave.setId(id);

        BlogPostDTO result = useCase.get(id);

        assertEquals(postText, result.getPost());
        assertEquals(toSave, blogPostDTO);
        assertEquals(toSave, result);
    }



    @Test
    public void saveListOfPost_getAll() throws Exception {
        List<BlogPostDTO> expected = testUtils.fillDatabaseWithTestData();

        List<Long> ids = useCase.getAllIds();
        List<BlogPostDTO> result = new ArrayList<>(ids.size());
        for (Long id : ids) {
            result.add(useCase.get(id));
        }

        assertEquals(3, result.size());
        for (int i = 0; i < 3; i++) {
            String expectedPost = expected.get(i).getPost();
            String resultPost = result.get(i).getPost();
            assertEquals(expectedPost, resultPost);
        }
        assertEquals(expected, result);
    }
}
