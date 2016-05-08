package com.shockn745.parsing;

import com.shockn745.domain.application.driven.MarkdownParser;
import org.pegdown.PegDownProcessor;

import static com.google.common.base.Strings.nullToEmpty;

/**
 * @author Kempenich Florian
 */
public class PegdownBasedParser implements MarkdownParser {

    private final PegDownProcessor pegDownProcessor;

    public PegdownBasedParser(PegDownProcessor pegDownProcessor) {
        this.pegDownProcessor = pegDownProcessor;
    }

    @Override
    public String toHtml(String markdown) {
        String html = pegDownProcessor.markdownToHtml(nullToEmpty(markdown));



        return html;
    }
}
