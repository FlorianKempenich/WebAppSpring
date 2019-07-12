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
        Pattern pattern = Pattern.compile("PICTURE:(.+)\\.(.+)\\.(.+)\\.\"(.+)?\"\\n");
        Matcher matcher = pattern.matcher(html);

        while (matcher.find()) {
            String imageTag = matcher.group();
            String imageName = matcher.group(1);
            String width = matcher.group(2);
            String height = matcher.group(3);
            String description = matcher.group(4);

            String imageLink = makeLocalImageLink(imageName, postId);


            html = html.replaceFirst(
                    imageTag,
                    makeImageDiv(imageLink, width, height, description)
            );
        }

        return html;
    }

    private String makeLocalImageLink(String imageName, int postId) {
        return "/assets/blog-post/" + postId + "/" + imageName + ".jpg";
    }

    private String makeImageDiv(String imageLink, String width, String height, String description) {
        description = nullToEmpty(description);
        //language=HTML
        return "<div class=\"row\">\n" +
                "    <div class=\"my-gallery\" itemscope=\"\" itemtype=\"https://schema.org/ImageGallery\">\n" +
                "        <figure itemprop=\"associatedMedia\" itemscope=\"\" itemtype=\"https://schema.org/ImageObject\"\n" +
                "                class=\"col-md-8 col-md-offset-2 gallery-item\">\n" +
                "            <a href=\"" + imageLink + "\" itemprop=\"contentUrl\"\n" +
                "               data-size=\"" + width + "x" + height + "\">\n" +
                "                <img src=\"" + imageLink + "\" itemprop=\"thumbnail\"\n" +
                "                     alt=\"" + description + "\" class=\"img-rounded img-responsive center-block\"></img>\n" +
                "            </a>\n" +
                "            <figcaption itemprop=\"caption description\" class=\"gallery-caption\">" + description + "\n" +
                "            </figcaption>\n" +
                "        </figure>\n" +
                "    </div>\n" +
                "</div>";
    }
}
