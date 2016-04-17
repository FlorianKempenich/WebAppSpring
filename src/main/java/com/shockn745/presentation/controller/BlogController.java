package com.shockn745.presentation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shockn745.TestUtils;
import com.shockn745.domain.application.driving.BlogPostUseCase;
import com.shockn745.domain.application.driving.dto.BlogPostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping(value = "/main")
    public String main(Model model) {
        // todo remove : Temp test
        testUtils.eraseDatabase();
        testUtils.fillDatabaseWithTestData();

        List<BlogPostDTO> posts = blogPostUseCase.getAll();

        try {
            model.addAttribute("posts", jacksonMapper.writeValueAsString(posts));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "yabe/yabe";
    }



    private String showTestMessage(Model model) {
        model.addAttribute("what", "This is a test");
        return "dev/what";
    }
}
