package com.shockn745.domain.application.driving.impl;

import com.shockn745.domain.application.driven.BlogPostRepository;
import com.shockn745.domain.application.driving.MainPageUseCase;
import com.shockn745.domain.application.driving.dto.BlogPostDTO;
import com.shockn745.domain.application.mapper.BlogPostMapper;
import com.shockn745.domain.core.BlogPost;
import com.shockn745.domain.core.PagesManager;
import com.shockn745.domain.core.PagesManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Kempenich Florian
 */
@Component
public class MainPageUseCaseImpl implements MainPageUseCase {

    private final BlogPostRepository blogPostRepository;
    private final PagesManagerFactory pagesManagerFactory;
    private final BlogPostMapper blogPostMapper;

    @Autowired
    public MainPageUseCaseImpl(BlogPostRepository blogPostRepository, PagesManagerFactory pagesManagerFactory, BlogPostMapper blogPostMapper) {
        this.blogPostRepository = blogPostRepository;
        this.pagesManagerFactory = pagesManagerFactory;
        this.blogPostMapper = blogPostMapper;
    }

    @Override
    public int getPageCount() {
        List<BlogPost> posts = getBlogPosts();
        PagesManager pagesManager = pagesManagerFactory.make(posts);
        return pagesManager.getPagesCount();
    }

    private List<BlogPost> getBlogPosts() {
        List<BlogPostDTO> postDTOs = blogPostRepository.getAll();
        return blogPostMapper.transformListDtoToDomain(postDTOs);
    }

    @Override
    public List<BlogPostDTO> getPage(int pageIndex) {
        List<BlogPost> posts = getBlogPosts();
        PagesManager pagesManager = pagesManagerFactory.make(posts);

        List<BlogPost> postsInPage = pagesManager.getPage(pageIndex);
        return blogPostMapper.transformListDomainToDto(postsInPage);
    }

    @Override
    public String getSummary(int postId) {
        BlogPost post = getBlogPost(postId);
        return post.summarize(300);
    }

    private BlogPost getBlogPost(int id) {
        BlogPostDTO fromRepository = blogPostRepository.get(id);
        return blogPostMapper.transform(fromRepository);
    }
}
