package com.shockn745.integrationtest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shockn745.TestUtils;
import com.shockn745.WebAppApplication;
import com.shockn745.domain.application.driving.dto.BlogPostDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebAppApplication.class)
@WebAppConfiguration
public class WebAppApplicationTests {

    @Autowired
    ObjectMapper jacksonMapper;
    @Autowired
    TestUtils testUtils;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testJackson() throws Exception {
        testUtils.eraseDatabase();
        List<BlogPostDTO> posts = testUtils.fillDatabaseWithTestData();

        String jsonPosts = jacksonMapper.writeValueAsString(posts);

        System.out.println(jsonPosts);
    }
}
