package com.shockn745.presentation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shockn745.TestUtils;
import com.shockn745.domain.application.driving.BlogPostUseCase;
import com.shockn745.domain.application.driving.dto.BlogPostDTO;
import org.pegdown.PegDownProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kempenich Florian
 */
@Controller
@RequestMapping(value = "/yabe")
public class BlogController {

    @Autowired
    BlogPostUseCase blogPostUseCase;

    @Autowired
    TestUtils testUtils;
    @Autowired
    ObjectMapper jacksonMapper;

    @RequestMapping(value = "/old")
    public String old(Model model) {
        // todo remove : Temp test
        testUtils.eraseDatabase();
        testUtils.fillDatabaseWithTestData();

        List<Long> postIds = blogPostUseCase.getAllIds();
        List<BlogPostDTO> posts = new ArrayList<>(postIds.size());
        for (Long postId : postIds) {
            posts.add(blogPostUseCase.get(postId));
        }
        try {
            model.addAttribute("posts", jacksonMapper.writeValueAsString(posts));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "yabe/yabe";
    }

    @RequestMapping(value = "/main")
    public String main(Model model) {

        List<Long> postIds = blogPostUseCase.getAllIds();
        List<BlogPostDTO> posts = new ArrayList<>(postIds.size());
        for (Long postId : postIds) {
            posts.add(blogPostUseCase.get(postId));
        }

        // Temp. Todo: Move
        PegDownProcessor processor = new PegDownProcessor();
        for (BlogPostDTO post : posts) {
            String text = post.getPost();
            String htmlText = processor.markdownToHtml(text);
            post.setPost(htmlText);
        }

        model.addAttribute("posts", posts);

        return "yabe/main";
    }

    @RequestMapping(value = "/paper")
    public String paper() {
        return "yabe/paper";
    }



    private String showTestMessage(Model model) {
        model.addAttribute("what", "This is a test");
        return "dev/what";
    }
}
