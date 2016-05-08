package com.shockn745;

import com.shockn745.domain.application.driven.MarkdownParser;
import com.shockn745.domain.application.mapper.BlogPostMapper;
import com.shockn745.domain.core.BlogPostFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

}