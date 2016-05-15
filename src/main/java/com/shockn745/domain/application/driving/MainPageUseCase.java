package com.shockn745.domain.application.driving;

import com.shockn745.domain.application.driving.dto.BlogPostDTO;

import java.util.List;

/**
 * @author Kempenich Florian
 */
public interface MainPageUseCase {

    int getPageCount();

    List<BlogPostDTO> getPage(int pageIndex);

    String getSummary(int postId);

    String getHtml(int postId);
}
