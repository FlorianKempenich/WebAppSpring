package com.shockn745.presentation.controller;

import com.shockn745.domain.application.driving.MainPageUseCase;
import com.shockn745.domain.application.driving.TagsUseCase;
import com.shockn745.domain.application.driving.dto.BlogPostDTO;
import com.shockn745.presentation.model.PostSummary;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "/tags")
public class BlogTagsController {

    @Autowired
    MainPageUseCase mainPageUseCase;
    @Autowired
    TagsUseCase tagsUseCase;

    @RequestMapping(value = "/{tag}")
    public String mainPage(@PathVariable String tag, Model model) {
        updateModelWithPageInformation(tag, 0, model);
        return "yabe/main";
    }

    @RequestMapping(value = "/{tag}/{pageIndex}")
    public String mainPage(@PathVariable String tag, @PathVariable int pageIndex, Model model) {
        if (pageIndex == 0) {
            return "redirect:/tags/" + tag; //to remove the '0' from the url
        } else {
            updateModelWithPageInformation(tag, pageIndex, model);
            return "yabe/main";
        }
    }

    private void updateModelWithPageInformation(String tag, int pageIndex, Model model) {
        int pageCount = tagsUseCase.getPageCount(tag);
        int indexOfPageToDisplay = getIndexOfPageToDisplay(pageIndex, pageCount);
        int indexOfNextPage = getIndexOfNextPage(indexOfPageToDisplay, pageCount);
        int indexOfPreviousPage = getIndexOfPreviousPage(indexOfPageToDisplay, pageCount);
        boolean isFirstPage = indexOfPageToDisplay == 0;
        boolean isLastPage = indexOfPageToDisplay == pageCount - 1;

        List<BlogPostDTO> posts = tagsUseCase.getPage(tag, indexOfPageToDisplay);
        List<PostSummary> postSummaries = makePostSummaries(posts);

        model.addAttribute("posts", postSummaries);
        model.addAttribute("currentTag", tag);
        model.addAttribute("linkOfNextPage", getLinkFromIndex(tag, indexOfNextPage));
        model.addAttribute("linkOfPreviousPage", getLinkFromIndex(tag, indexOfPreviousPage));
        model.addAttribute("isFirstPage", isFirstPage);
        model.addAttribute("isLastPage", isLastPage);
    }

    private String getLinkFromIndex(String tag, int index) {
        return "/tags/" + tag + "/" + index;
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
        } else if (pageIndexFromUrl >= pageCount) {
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


}
