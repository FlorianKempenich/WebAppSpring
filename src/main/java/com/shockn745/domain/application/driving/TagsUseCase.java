package com.shockn745.domain.application.driving;

import com.shockn745.domain.application.driving.dto.BlogPostDTO;

import java.util.List;

/**
 * This Use case handle all actions related to tags :
 *  - Display pages for a specific tag
 *  - List of popular tags
 *
 * @author Kempenich Florian
 */
public interface TagsUseCase {

    int getPageCount(String tag);

    List<BlogPostDTO> getPage(String tag, int pageIndex);
}
