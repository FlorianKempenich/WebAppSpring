package com.shockn745.presentation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shockn745.TestUtils;
import com.shockn745.domain.application.driving.BlogPostUseCase;
import com.shockn745.domain.application.driving.MainPageUseCase;
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

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Kempenich Florian
 */
@Controller
@RequestMapping(value = "/")
public class BlogController {

    @Autowired
    BlogPostUseCase blogPostUseCase;
    @Autowired
    MainPageUseCase mainPageUseCase;

    @Autowired
    TestUtils testUtils;
    @Autowired
    ObjectMapper jacksonMapper;
    @Autowired
    Disqus disqus;

    @Autowired
    Environment environment;

    @RequestMapping(value = "/")
    public String mainPage(Model model) {
        updateModelWithPageInformation(0, model);
        return "yabe/main";
    }

    @RequestMapping(value = "/{pageIndex}")
    public String followingPages(@PathVariable int pageIndex, Model model) {
        if (pageIndex == 0) {
            return "redirect:"; //to remove the '0' from the url
        } else {
            updateModelWithPageInformation(pageIndex, model);
            return "yabe/main";
        }
    }

    private void updateModelWithPageInformation(@PathVariable int pageIndex, Model model) {
        int pageCount = mainPageUseCase.getPageCount();
        int indexOfPageToDisplay = getIndexOfPageToDisplay(pageIndex, pageCount);
        System.out.println(indexOfPageToDisplay);
        System.out.println(pageCount);
        int indexOfNextPage = getIndexOfNextPage(indexOfPageToDisplay, pageCount);
        int indexOfPreviousPage = getIndexOfPreviousPage(indexOfPageToDisplay, pageCount);
        boolean isFirstPage = indexOfPageToDisplay == 0;
        boolean isLastPage = indexOfPageToDisplay == pageCount - 1;

        List<BlogPostDTO> posts = mainPageUseCase.getPage(indexOfPageToDisplay);
        List<PostSummary> postSummaries = makePostSummaries(posts);

        model.addAttribute("posts", postSummaries);
        model.addAttribute("indexOfNextPage", indexOfNextPage);
        model.addAttribute("indexOfPreviousPage", indexOfPreviousPage);
        model.addAttribute("isFirstPage", isFirstPage);
        model.addAttribute("isLastPage", isLastPage);
    }

    private int getIndexOfPreviousPage(int pageIndexCurrentlyDisplayed, int pageCount) {
        checkArgument(pageIndexCurrentlyDisplayed >= 0 && pageIndexCurrentlyDisplayed < pageCount);

        int indexOfPreviousPage;
        if (pageIndexCurrentlyDisplayed == 0) {
            indexOfPreviousPage = pageIndexCurrentlyDisplayed;
        } else {
            indexOfPreviousPage = pageIndexCurrentlyDisplayed - 1;
        }

        return indexOfPreviousPage;
    }

    private int getIndexOfPageToDisplay(int pageIndexFromUrl, int pageCount) {
        int indexOfPageToDisplay;
        if (pageIndexFromUrl < 0) {
            indexOfPageToDisplay = 0;
        } else if (pageIndexFromUrl >= pageCount){
            indexOfPageToDisplay = pageCount - 1; // last page
        } else {
            indexOfPageToDisplay = pageIndexFromUrl;
        }
        return indexOfPageToDisplay;
    }

    private int getIndexOfNextPage(int pageIndexCurrentlyDisplayed, int pageCount) {
        checkArgument(pageIndexCurrentlyDisplayed >= 0 && pageIndexCurrentlyDisplayed < pageCount);

        int indexOfNextPage;
        if (pageIndexCurrentlyDisplayed == pageCount - 1) {
            indexOfNextPage = pageIndexCurrentlyDisplayed;
        } else {
            indexOfNextPage = pageIndexCurrentlyDisplayed + 1;
        }

        return indexOfNextPage;
    }

    private List<PostSummary> makePostSummaries(List<BlogPostDTO> posts) {
        List<PostSummary> postSummaries = new ArrayList<>();
        for (BlogPostDTO post : posts) {
            String summary = mainPageUseCase.getSummary(post.getId());
            postSummaries.add(new PostSummary(post.getId(), post.getTitle(), summary, post.getDate(), post.getTags()));
        }
        return postSummaries;
    }


    @RequestMapping(value = "/post/{id}")
    public String postWithId(@PathVariable int id, Model model) {
        BlogPostDTO post = blogPostUseCase.get(id);

        String title = post.getTitle();
        String htmlContent = mainPageUseCase.getHtml(id);

        model.addAttribute("title", title);
        model.addAttribute("htmlContent", htmlContent);

        model.addAttribute("page_url", disqus.getPageUrl(id));
        model.addAttribute("page_identifier", disqus.getPageIdentifier(id));

        return "yabe/post";
    }

    @RequestMapping(value = "/manifesto")
    public String showManifesto(Model model) {
        // By convention manifesto id == 1
        return postWithId(1, model);
    }

    private String showTestMessage(Model model) {
        model.addAttribute("what", "This is a test");
        return "dev/what";
    }
}
