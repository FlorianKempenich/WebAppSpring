package com.shockn745.domain.application.driven;

import com.shockn745.domain.core.BlogPost;

/**
 * @author Kempenich Florian
 */
public interface BlogPostRepository {

    BlogPost getBlogPost(int id);
}
