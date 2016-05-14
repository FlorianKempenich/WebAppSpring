package com.shockn745.domain.core;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Kempenich Florian
 */
public class PagesManager {


    private final List<BlogPost> posts;
    private final int postsPerPage;
    private int pagesCount;

    public PagesManager(List<BlogPost> posts, int postsPerPage) {
        checkNotNull(posts);
        checkValid(postsPerPage);
        this.postsPerPage = postsPerPage;
        this.posts = posts;
        computePagesCount();
    }

    private static void checkValid(int postsPerPage) {
        if (postsPerPage <= 0) {
            throw new IllegalStateException("Please use a number of Posts per pages > 0");
        }
    }

    private void computePagesCount() {
        if (posts.isEmpty()) {
            pagesCount = 1;
        } else {
            if (posts.size() % postsPerPage == 0) {
                pagesCount = posts.size() / postsPerPage;
            } else {
                pagesCount = posts.size() / postsPerPage + 1;
            }
        }
    }

    /**
     * @param pageIndex starts at 0
     * @return The list of blog posts contained in the requested page
     */
    public List<BlogPost> getPage(int pageIndex) {
        if (posts.isEmpty()) {
            return new ArrayList<>();
        }

        if (hasOddLastPage() && isLastPage(pageIndex)) {
            return makeOddLastPage();
        } else {
            return makeRegularPage(pageIndex);
        }
    }

    private List<BlogPost> makeRegularPage(int pageIndex) {
        int startIndex = pageIndex * postsPerPage;
        int endIndex = (pageIndex + 1) * postsPerPage;

        return posts.subList(startIndex, endIndex);
    }

    private List<BlogPost> makeOddLastPage() {
        int postsSize = posts.size();
        int numberPostLastPage = postsSize % postsPerPage;

        return posts.subList(postsSize - numberPostLastPage, postsSize);
    }

    private boolean hasOddLastPage() {
        return posts.size() % postsPerPage != 0;
    }

    private boolean isLastPage(int pageIndex) {
        return pageIndex == pagesCount - 1;
    }

    public int getPagesCount() {
        return pagesCount;
    }
}
