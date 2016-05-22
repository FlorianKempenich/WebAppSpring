package com.shockn745.domain.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.base.Strings.nullToEmpty;

/**
 * @author Kempenich Florian
 */
class Summarizer {

    private String markdownContent;

    synchronized String getSummary(String markdownContent, int charLimit) {
        this.markdownContent = nullToEmpty(markdownContent);
        removeMarkdownMarkup();
        truncatePostInLength(charLimit);
        truncateAtFirstTitle();

        addEllipsis();
        return this.markdownContent;
    }

    private void removeMarkdownMarkup() {
        removeBoldAndItalicMarkup();
        removeLinks();
    }

    private void removeLinks() {
        Pattern pattern = Pattern.compile("\\[(.*)\\]\\(.*\\)");
        Matcher matcher = pattern.matcher(markdownContent);

        while (matcher.find()) {
            markdownContent = markdownContent.replace(matcher.group(), matcher.group(1));
        }
    }

    private void removeBoldAndItalicMarkup() {
        markdownContent = markdownContent.replaceAll("\\*", "");
    }

    private void addEllipsis() {
        removeSpecialCharacterAtTheEnd();
        markdownContent = markdownContent + " . . .";
    }

    /**
     * Removes the following characters from the end of the string : " ", "\n", '."
     */
    private void removeSpecialCharacterAtTheEnd() {
        markdownContent = markdownContent.replaceAll("[ \\n.]$", "");
    }

    private void truncateAtFirstTitle() {
        int indexOfFirstTitle = markdownContent.indexOf("#");
        if (indexOfFirstTitle != -1) {
            markdownContent = markdownContent.substring(0, indexOfFirstTitle);
        }
    }

    private void truncatePostInLength(int charLimit) {
        if (markdownContent.length() > charLimit) {
            while (inMiddleOfWord(charLimit)) {
                charLimit--;
            }
            markdownContent = markdownContent.substring(0, charLimit);
        }
    }

    private boolean inMiddleOfWord(int charLimit) {
        char atLimit = markdownContent.charAt(charLimit);
        char afterLimit = markdownContent.charAt(charLimit + 1);
        return Character.isLetter(atLimit) && Character.isLetter(afterLimit);
    }
}
