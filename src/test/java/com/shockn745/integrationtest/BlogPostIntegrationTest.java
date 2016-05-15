package com.shockn745.integrationtest;

import com.shockn745.TestUtils;
import com.shockn745.WebAppApplication;
import com.shockn745.domain.application.driving.BlogPostDetailUseCase;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author Kempenich Florian
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebAppApplication.class)
@WebAppConfiguration
@ActiveProfiles("integration-test")
public class BlogPostIntegrationTest {


    @Autowired
    BlogPostDetailUseCase useCase;
    @Autowired
    TestUtils testUtils;

    private String postText = "Hello this is blog postText";

    @Before
    public void setUp() throws Exception {
        testUtils.eraseDatabase();
    }

    @Test
    @Ignore
    public void saveAndRetrieve_samePost() throws Exception {
        // deprecated

//        BlogPostDTO toSave = TestUtils.makePost("title", postText);
//        BlogPostDTO blogPostDTO = useCase.save(toSave);
//        int id = blogPostDTO.getId();
//        toSave.setId(id);
//
//        BlogPostDTO result = useCase.get(id);
//
//        assertEquals(postText, result.getMarkdownPost());
//        assertEquals(toSave, blogPostDTO);
//        assertEquals(toSave, result);
    }



    @Test
    @Ignore
    public void saveListOfPost_getAll() throws Exception {
        // deprecated


//        List<BlogPostDTO> expected = testUtils.fillDatabaseWithTestData();
//
//        List<Integer> ids = useCase.getAllIds();
//        List<BlogPostDTO> result = new ArrayList<>(ids.size());
//        for (int id : ids) {
//            result.add(useCase.get(id));
//        }
//
//        assertEquals(4, result.size());
//        for (int i = 0; i < 4; i++) {
//            String expectedPost = expected.get(i).getMarkdownPost();
//            String resultPost = result.get(i).getMarkdownPost();
//            assertEquals(expectedPost, resultPost);
//        }
//        assertEquals(expected, result);
    }
}
