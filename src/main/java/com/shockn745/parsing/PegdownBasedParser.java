package com.shockn745.parsing;

import com.shockn745.domain.application.driven.MarkdownParser;
import org.pegdown.PegDownProcessor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public String toHtml(String markdown, int postId) {
        String pictureParsed = parsePictures(nullToEmpty(markdown), postId);
        return pegDownProcessor.markdownToHtml(pictureParsed);
    }

    private String parsePictures(String html, int postId) {
        Pattern pattern = Pattern.compile("PICTURE:(.+)");
        Matcher matcher = pattern.matcher(html);

        while (matcher.find()) {
            String imageTag = matcher.group();
            String imageName = matcher.group(1);

            html = html.replace(
                    imageTag,
                    makeImageDiv(imageName, postId)
            );
        }

        return html;
    }

    private String makeImageDiv(String imageName, int postId) {
        return "<div class=\"card-image card-with-shadow\">\n" +
                "    <img src=\"" + "/assets/blog-post/" + postId + "/" + imageName + ".jpg" +
                "\" alt=\"Rounded Image\" class=\"img-rounded img-responsive\">\n" +
                "</div>\n";
    }
}
