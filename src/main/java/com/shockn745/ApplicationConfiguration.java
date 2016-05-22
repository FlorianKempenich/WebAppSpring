package com.shockn745;

import com.shockn745.domain.application.driven.BlogPostRepository;
import com.shockn745.domain.application.driven.MarkdownParser;
import com.shockn745.domain.application.mapper.BlogPostMapper;
import com.shockn745.domain.core.BlogPostFactory;
import com.shockn745.domain.core.PagesManagerFactory;
import com.shockn745.domain.core.TagManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author Kempenich Florian
 */
@Configuration
public class ApplicationConfiguration {

    @Bean
    public BlogPostMapper getBlogPostMapper(MarkdownParser parser) {
        BlogPostFactory blogPostFactory = new BlogPostFactory(parser);
        return new BlogPostMapper(blogPostFactory);
    }

    @Bean
    public PagesManagerFactory getPagesManagerFactory(@Qualifier("posts-per-page") int postsPerPage) {
        return new PagesManagerFactory(postsPerPage);
    }

    @Bean
    public TagManager getTagManager(BlogPostRepository blogPostRepository, BlogPostMapper blogPostMapper) {
        return new TagManager(blogPostRepository, blogPostMapper);
    }

    @Bean
    @Qualifier("posts-per-page")
    @Profile("prod")
    public int getPostsPerPage_prod() {
        return 3;
    }

    @Bean
    @Qualifier("posts-per-page")
    @Profile("dev")
    public int getPostsPerPage_dev() {
        return 2;
    }

    @Bean
    @Qualifier("posts-per-page")
    @Profile("integration-test")
    public int getPostsPerPage_test() {
        return 2;
    }

}