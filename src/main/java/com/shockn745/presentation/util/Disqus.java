package com.shockn745.presentation.util;

import org.springframework.stereotype.Component;

/**
 * @author Kempenich Florian
 */
@Component
public class Disqus {

    public String getPageUrl(int id) {
        return "http://www.professionalbeginner.com/post/" + id;
    }

    public String getPageIdentifier(int id) {
        return "blog-post-id-" + id;
    }

}
