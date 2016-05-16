package com.shockn745.domain.application.driving.impl;

import com.shockn745.domain.application.driving.TagsUseCase;
import com.shockn745.domain.application.driving.dto.BlogPostDTO;
import com.shockn745.domain.application.mapper.BlogPostMapper;
import com.shockn745.domain.core.BlogPost;
import com.shockn745.domain.core.PagesManager;
import com.shockn745.domain.core.PagesManagerFactory;
import com.shockn745.domain.core.TagManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Kempenich Florian
 */
@Component
public class TagsUseCaseImpl implements TagsUseCase {

    private final TagManager tagManager;
    private final PagesManagerFactory pagesManagerFactory;
    private final BlogPostMapper mapper;

    @Autowired
    public TagsUseCaseImpl(TagManager tagManager, PagesManagerFactory pagesManagerFactory, BlogPostMapper mapper) {
        checkNotNull(tagManager);
        checkNotNull(pagesManagerFactory);
        checkNotNull(mapper);
        this.tagManager = tagManager;
        this.pagesManagerFactory = pagesManagerFactory;
        this.mapper = mapper;
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
    public List<BlogPostDTO> getPage(String tag, int pageIndex) {
        PagesManager pagesManagerForTag = getPagesManagerForTag(tag);
        List<BlogPost> page = pagesManagerForTag.getPage(pageIndex);
        return mapper.transformListDomainToDto(page);
    }

    @Override
    public List<String> getPopularTags(int limit) {
        return tagManager.getPopularTags(limit);
    }
}
