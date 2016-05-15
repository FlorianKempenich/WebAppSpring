package com.shockn745.domain.core;

import java.util.List;

/**
 * @author Kempenich Florian
 */
public class PagesManagerFactory {

    private final int postsPerPage;

    public PagesManagerFactory(int postsPerPage) {
        this.postsPerPage = postsPerPage;
    }

    public PagesManager make(List<BlogPost> blogPosts) {
        return new PagesManager(blogPosts, postsPerPage);
    }
}
