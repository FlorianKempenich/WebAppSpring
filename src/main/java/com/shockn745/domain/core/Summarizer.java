package com.shockn745.domain.core;

import static com.google.common.base.Strings.nullToEmpty;

/**
 * @author Kempenich Florian
 */
class Summarizer {

    private String markdownContent;

    synchronized String getSummary(String markdownContent, int charLimit) {
        this.markdownContent = nullToEmpty(markdownContent);
        String truncatedInLength = truncatePostInLength(charLimit);
        String truncatedAtFirstTitle = truncateAtFirstTitle(truncatedInLength);
        String withoutMarkdownMarkup = removeMarkdownMarkup(truncatedAtFirstTitle);

        return addEllipsis(withoutMarkdownMarkup);
    }

    private String removeMarkdownMarkup(String truncatedAtFirstTitle) {
        return truncatedAtFirstTitle.replaceAll("\\*", "");
    }

    private String addEllipsis(String text) {
        text = removeSpecialCharacterAtTheEnd(text);
        return text + " . . .";
    }

    /**
     * Removes the following characters from the end of the string : " ", "\n", '."
     */
    private String removeSpecialCharacterAtTheEnd(String text) {
        return text.replaceAll("[ \\n.]$", "");
    }

    private String truncateAtFirstTitle(String truncated) {
        int indexOfFirstTitle = truncated.indexOf("#");
        if (indexOfFirstTitle != -1) {
            return truncated.substring(0, indexOfFirstTitle);
        } else {
            return truncated;
        }
    }

    private String truncatePostInLength(int charLimit) {
        if (markdownContent.length() > charLimit) {
            while (inMiddleOfWord(charLimit)) {
                charLimit--;
            }
            return markdownContent.substring(0, charLimit);
        } else {
            return markdownContent;
        }
    }

    private boolean inMiddleOfWord(int charLimit) {
        char atLimit = markdownContent.charAt(charLimit);
        char afterLimit = markdownContent.charAt(charLimit + 1);
        return Character.isLetter(atLimit) && Character.isLetter(afterLimit);
    }
}
