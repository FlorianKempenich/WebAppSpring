package com.shockn745;

import com.shockn745.data.blogpost.jpa.BlogPostRepositoryImpl;
import com.shockn745.data.blogpost.file.InFileBlogPostRepositoryImpl;
import com.shockn745.data.blogpost.jpa.JpaBlogPostRepo;
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
    public BlogPostRepository getBlogPostRepository_prod(Path database) {
        return new InFileBlogPostRepositoryImpl(database);
    }

    @Bean
    @Qualifier("in-file-database-path")
    public Path getInFileDatabaseDirectoryPath_prod() {
//      return Paths.get("..", "Articles");
        return Paths.get("/posts");
    }

    @Bean
    @Profile("integration-test")
    public BlogPostRepository getBlogPostRepository_integration_tests(JpaBlogPostRepo jpaBlogPostRepo) {
        return new BlogPostRepositoryImpl(jpaBlogPostRepo);
    }
}
