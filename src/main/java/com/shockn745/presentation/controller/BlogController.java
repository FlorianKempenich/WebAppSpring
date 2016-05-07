package com.shockn745.presentation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shockn745.TestUtils;
import com.shockn745.domain.application.driving.BlogPostUseCase;
import com.shockn745.domain.application.driving.dto.BlogPostDTO;
import com.shockn745.presentation.model.PostSummary;
import org.pegdown.Extensions;
import org.pegdown.PegDownProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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

    @Autowired
    Environment environment;

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


        PegDownProcessor processor = new PegDownProcessor(Extensions.FENCED_CODE_BLOCKS);
        for (BlogPostDTO post : posts) {
            String text = post.getPost();
            String htmlText = processor.markdownToHtml(text);
            post.setPost(htmlText);
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

        // FIXME: 5/7/2016 Remove only for demo purposes
        if (environment.getActiveProfiles()[0].equals("prod")) {
            testUtils.fillDatabaseWithTestData();
        }

        List<Long> postIds = blogPostUseCase.getAllIds();
        List<PostSummary> postSummaries = new ArrayList<>();
        for (Long postId : postIds) {
            BlogPostDTO post = blogPostUseCase.get(postId);
            String summary = blogPostUseCase.getSummary(postId);

            postSummaries.add(new PostSummary(post.getTitle(), summary));
        }

//        // Temp. Todo: Move
//        PegDownProcessor processor = new PegDownProcessor();
//        for (BlogPostDTO post : posts) {
//            String text = post.getPost();
//            String htmlText = processor.markdownToHtml(text);
//            post.setPost(htmlText);
//        }





        model.addAttribute("posts", postSummaries);

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


    @RequestMapping(value = "/post")
    public String post(Model model) {

        return "yabe/post";
    }
}
