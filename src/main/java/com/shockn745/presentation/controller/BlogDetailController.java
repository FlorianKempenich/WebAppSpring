package com.shockn745.presentation.controller;

import com.shockn745.domain.application.driving.BlogPostDetailUseCase;
import com.shockn745.domain.application.driving.TagsUseCase;
import com.shockn745.domain.application.driving.dto.BlogPostDTO;
import com.shockn745.presentation.util.Disqus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Kempenich Florian
 */
@Controller
@RequestMapping(value = "/")
public class BlogDetailController {

    @Autowired
    BlogPostDetailUseCase blogPostDetailUseCase;
    @Autowired
    TagsUseCase tagsUseCase;

    @Autowired
    Disqus disqus;

    @Autowired
    Environment environment;

    @RequestMapping(value = "/about")
    public String showAbout(Model model) {
        // By convention 'About page' id == 1
        updateModelWithPostInformation(1, model);
        return "yabe/post";
    }

    private void updateModelWithPostInformation(int postId, Model model) {
        BlogPostDTO post = blogPostDetailUseCase.get(postId);

        String title = post.getTitle();
        String htmlContent = blogPostDetailUseCase.getHtml(postId);

        updateModelWithPopularTags(model);

        model.addAttribute("title", title);
        model.addAttribute("htmlContent", htmlContent);

        model.addAttribute("page_url", disqus.getPageUrl(postId));
        model.addAttribute("page_identifier", disqus.getPageIdentifier(postId));
    }

    private void updateModelWithPopularTags(Model model) {
        model.addAttribute("popularTags", tagsUseCase.getPopularTags(8));
    }

    @RequestMapping(value = "/post/{id}")
    public String postWithId(@PathVariable int id, Model model) {
        updateModelWithPostInformation(id, model);
        return "yabe/post";
    }
}
