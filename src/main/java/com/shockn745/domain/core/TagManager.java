package com.shockn745.domain.core;

import com.shockn745.domain.application.driven.BlogPostRepository;
import com.shockn745.domain.application.driving.dto.BlogPostDTO;
import com.shockn745.domain.application.mapper.BlogPostMapper;

import java.util.*;

/**
 * @author Kempenich Florian
 */
public class TagManager {

    private final BlogPostRepository blogPostRepository;
    private final BlogPostMapper blogPostMapper;

    public TagManager(BlogPostRepository blogPostRepository, BlogPostMapper blogPostMapper) {
        this.blogPostRepository = blogPostRepository;
        this.blogPostMapper = blogPostMapper;
    }

    public List<BlogPost> findPosts(String tag) {
        List<BlogPostDTO> allPosts = blogPostRepository.getAll();

        List<BlogPost> result = new ArrayList<>();
        for (BlogPostDTO post : allPosts) {
            if (isMatch(tag, post)) {
                result.add(blogPostMapper.transform(post));
            }
        }

        return result;
    }

    private boolean isMatch(String tag, BlogPostDTO post) {
        List<String> postTags = post.getTags();

        for (String postTag : postTags) {
            if (isTagMatch(tag, postTag)) {
                return true;
            }
        }

        return false;
    }

    private boolean isTagMatch(String tag, String postTag) {
        return postTag.trim().equalsIgnoreCase(tag);
    }

    public List<String> getPopularTags(int limit) {
        List<String> allTags = getAllTagsFormatted();
        Map<String, Integer> tagWithFrequency = computeFrequencies(allTags);

        List<String> popularTags = makePopularTagList(tagWithFrequency);

        List<String> result;
        if (popularTags.size() > limit) {
            result = popularTags.subList(0, limit);
        } else {
            result = popularTags;
        }

        return result;
    }

    private List<String> makePopularTagList(Map<String, Integer> tagWithFrequency) {
        List<String> result = new ArrayList<>(tagWithFrequency.keySet());
        Collections.sort(result, new TagFrequencyComparator(tagWithFrequency));
        return result;
    }

    private Map<String, Integer> computeFrequencies(List<String> allTags) {
        Map<String, Integer> tagWithFrequency = new HashMap<>();
        for (String tag : allTags) {
            int freq = 0;
            if (tagWithFrequency.containsKey(tag)) {
                freq = tagWithFrequency.get(tag);
            }
            freq++;
            tagWithFrequency.put(tag, freq);
        }
        return tagWithFrequency;
    }

    private List<String> getAllTagsFormatted() {
        List<BlogPostDTO> allPosts = blogPostRepository.getAll();
        List<String> allTags = new ArrayList<>();
        for (BlogPostDTO post : allPosts) {
            for (String tag : post.getTags()) {

                String formattedTags = tag.trim().toLowerCase();
                allTags.add(formattedTags);

            }
        }
        return allTags;
    }


    private static class TagFrequencyComparator implements Comparator<String> {

        private final Map<String, Integer> tagWithFrequency;

        public TagFrequencyComparator(Map<String, Integer> tagWithFrequency) {
            this.tagWithFrequency = tagWithFrequency;
        }

        @Override
        public int compare(String first, String second) {
            Integer firstFreq = tagWithFrequency.get(first);
            Integer secondFreq = tagWithFrequency.get(second);
            return -1 * firstFreq.compareTo(secondFreq);
        }
    }
}
