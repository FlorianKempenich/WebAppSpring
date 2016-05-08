package com.shockn745.presentation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shockn745.TestUtils;
import com.shockn745.domain.application.driving.BlogPostUseCase;
import com.shockn745.domain.application.driving.dto.BlogPostDTO;
import com.shockn745.presentation.model.PostSummary;
import com.shockn745.presentation.util.Disqus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
    Disqus disqus;

    @Autowired
    Environment environment;

    @RequestMapping(value = "/old")
    public String old(Model model) {
//        // todo remove : Temp test
//        testUtils.eraseDatabase();
//        testUtils.fillDatabaseWithTestData();
//
//        List<Integer> postIds = blogPostUseCase.getAllIds();
//        List<BlogPostDTO> posts = new ArrayList<>(postIds.size());
//        for (int postId : postIds) {
//            posts.add(blogPostUseCase.get(postId));
//        }
//
//        try {
//            model.addAttribute("posts", jacksonMapper.writeValueAsString(posts));
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
        return "yabe/yabe";
    }

    @RequestMapping(value = "/main")
    public String main(Model model) {

        // FIXME: 5/7/2016 Remove only for demo purposes
        if (inProduction()) {
            testUtils.fillDatabaseWithTestData();
        }

        List<Integer> postIds = blogPostUseCase.getAllIds();
        List<PostSummary> postSummaries = makePostSummaries(postIds);

        model.addAttribute("posts", postSummaries);

        return "yabe/main";
    }

    private List<PostSummary> makePostSummaries(List<Integer> postIds) {
        List<PostSummary> postSummaries = new ArrayList<>();
        for (int postId : postIds) {
            BlogPostDTO post = blogPostUseCase.get(postId);
            String summary = blogPostUseCase.getSummary(postId);
            postSummaries.add(new PostSummary(post.getTitle(), summary));
        }
        return postSummaries;
    }

    private boolean inProduction() {
        return environment.getActiveProfiles()[0].equals("prod");
    }

    @RequestMapping(value = "/paper")
    public String paper() {
        return "yabe/paper";
    }

    @RequestMapping(value = "/post")
    public String post(Model model) {
        if (inProduction()) {
            testUtils.fillDatabaseWithTestData();
        }
        List<Integer> postIds = blogPostUseCase.getAllIds();

        if (postIds.contains(1000)) {
            return postWithId(1000, model);
        } else {
            return postWithId(1, model);
        }
    }

    @RequestMapping(value = "/post/{id}")
    public String postWithId(@PathVariable int id, Model model) {

        BlogPostDTO post = blogPostUseCase.get(id);

        String title = post.getTitle();
        String htmlContent = blogPostUseCase.getHtml(id);

        model.addAttribute("title", title);
        model.addAttribute("htmlContent", htmlContent);

        model.addAttribute("page_url", disqus.getPageUrl(id));
        model.addAttribute("page_identifier", disqus.getPageIdentifier(id));

        return "yabe/post";
    }

    // todo remove
    private BlogPostDTO replacePicture(BlogPostDTO blogPostDTO) {
        String post = blogPostDTO.getMarkdownPost();

        String pathToAsset = "/assets/blog-post";
        String src = pathToAsset + "/" + "1000" + "/" + "space.jpg";

        @SuppressWarnings("HtmlUnknownTarget")
        String postWithImage = post.replace("PICTURE", "<div class=\"card-image card-with-shadow\">\n" +
                "    <img src=\"" + src + "\" alt=\"Rounded Image\" class=\"img-rounded img-responsive\">\n" +
                "</div>\n");
        blogPostDTO.setMarkdownPost(postWithImage);
        return blogPostDTO;
    }

    private String showTestMessage(Model model) {
        model.addAttribute("what", "This is a test");
        return "dev/what";
    }
}
