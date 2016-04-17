package com.shockn745.integrationtest.data;

import com.shockn745.WebAppApplication;
import com.shockn745.data.jpa.JpaBlogPostRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertNotNull;

/**
 * @author Kempenich Florian
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebAppApplication.class)
@WebAppConfiguration
public class JpaBlogPostRepoIntegrationTest {


    @Autowired
    JpaBlogPostRepo jpaBlogPostRepo;

    @Before
    public void setUp() throws Exception {
        jpaBlogPostRepo.deleteAll();
    }

    @Test
    public void findAll_neverReturnNull() throws Exception {
        assertNotNull(jpaBlogPostRepo.findAll());

    }
}
