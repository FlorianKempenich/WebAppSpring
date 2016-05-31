package com.shockn745.network;

import org.junit.Test;

/**
 * @author Kempenich Florian
 */
public class TestNewsletterRepositoryImplSpring {

    SpringNewsletterRepositoryImpl repository = new SpringNewsletterRepositoryImpl();



    @Test
    public void name() throws Exception {

    }

    @Test
    public void test_save() throws Exception {
        repository.save("florian@gmail.com");
    }
}