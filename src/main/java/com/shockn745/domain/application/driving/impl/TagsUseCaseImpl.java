package com.shockn745.domain.application.driving.impl;

import com.shockn745.domain.application.driving.TagsUseCase;
import com.shockn745.domain.core.BlogPost;
import com.shockn745.domain.core.PagesManager;
import com.shockn745.domain.core.PagesManagerFactory;
import com.shockn745.domain.core.TagManager;

import java.util.List;

/**
 * @author Kempenich Florian
 */
public class TagsUseCaseImpl implements TagsUseCase{

    private final TagManager tagManager;
    private final PagesManagerFactory pagesManagerFactory;

    public TagsUseCaseImpl(TagManager tagManager, PagesManagerFactory pagesManagerFactory) {
        this.tagManager = tagManager;
        this.pagesManagerFactory = pagesManagerFactory;
    }

    @Override
    public int getPageCount(String tag) {
        PagesManager pagesManagerForTag = getPagesManagerForTag(tag);

        return pagesManagerForTag.getPagesCount();
    }

    private PagesManager getPagesManagerForTag(String tag) {
        List<BlogPost> postsForTag = tagManager.findPosts(tag);
        return pagesManagerFactory.make(postsForTag);
    }

    @Override
    public List<BlogPost> getPage(String tag, int pageIndex) {
        PagesManager pagesManagerForTag = getPagesManagerForTag(tag);
        return pagesManagerForTag.getPage(pageIndex);
    }
}
