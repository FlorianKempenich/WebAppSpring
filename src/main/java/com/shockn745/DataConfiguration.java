package com.shockn745;

import com.shockn745.data.BlogPostRepositoryImpl;
import com.shockn745.data.InFileBlogPostRepositoryImpl;
import com.shockn745.data.jpa.JpaBlogPostRepo;
import com.shockn745.domain.application.driven.BlogPostRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Kempenich Florian
 */
@Configuration
public class DataConfiguration {

    @Bean
    @Qualifier("secret_store_file_path")
    public String getSecretStorePath() {
        return "secret_store";
    }

    @Bean
    @Profile("dev")
    public BlogPostRepository getBlogPostRepository_dev(Path database) {
        return new InFileBlogPostRepositoryImpl(database);
    }

    @Bean
    @Qualifier("in-file-database-path")
    public Path getInFileDatabaseDirectoryPath() {
        return Paths.get("file_database", "blog_posts");
    }

    @Bean
    @Profile("prod")
    public BlogPostRepository getBlogPostRepository_prod(JpaBlogPostRepo jpaBlogPostRepo) {
        return new BlogPostRepositoryImpl(jpaBlogPostRepo);
    }

    @Bean
    @Profile("integration-test")
    public BlogPostRepository getBlogPostRepository_integration_tests(JpaBlogPostRepo jpaBlogPostRepo) {
        return new BlogPostRepositoryImpl(jpaBlogPostRepo);
    }
}
