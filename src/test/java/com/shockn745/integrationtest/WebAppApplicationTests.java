package com.shockn745.integrationtest;

import com.shockn745.WebAppApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebAppApplication.class)
@WebAppConfiguration
public class WebAppApplicationTests {

	@Test
	public void contextLoads() {
	}

}
