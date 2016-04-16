package com.shockn745.data;

import com.shockn745.domain.application.driven.BlogPostRepository;
import com.shockn745.domain.core.BlogPost;
import org.springframework.stereotype.Component;

/**
 * @author Kempenich Florian
 */
@Component
public class FakeBlogPostRepository implements BlogPostRepository {

    @Override
    public BlogPost getBlogPost(int id) {
        return new BlogPost(
                "An h1 header\n" +
                        "============\n" +
                        "\n" +
                        "Paragraphs are separated by a blank line.\n" +
                        "\n" +
                        "2nd paragraph. *Italic*, **bold**, and `monospace`. Itemized lists\n" +
                        "look like:\n" +
                        "\n" +
                        "  * this one\n" +
                        "  * that one\n" +
                        "  * the other one\n" +
                        "\n" +
                        "Note that --- not considering the asterisk --- the actual text\n" +
                        "content starts at 4-columns in.\n" +
                        "\n" +
                        "> Block quotes are\n" +
                        "> written like so.\n" +
                        ">\n" +
                        "> They can span multiple paragraphs,\n" +
                        "> if you like.\n" +
                        "\n" +
                        "Use 3 dashes for an em-dash. Use 2 dashes for ranges (ex., \"it's all\n" +
                        "in chapters 12--14\"). Three dots ... will be converted to an ellipsis.\n" +
                        "Unicode is supported. ☺\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "An h2 header\n" +
                        "------------\n" +
                        "\n" +
                        "Here's a numbered list:\n" +
                        "\n" +
                        " 1. first item\n" +
                        " 2. second item\n" +
                        " 3. third item\n" +
                        "\n" +
                        "Note again how the actual text starts at 4 columns in (4 characters\n" +
                        "from the left side). Here's a code sample:\n" +
                        "\n" +
                        "    # Let me re-iterate ...\n" +
                        "    for i in 1 .. 10 { do-something(i) }\n" +
                        "\n" +
                        "As you probably guessed, indented 4 spaces. By the way, instead of\n" +
                        "indenting the block, you can use delimited blocks, if you like:\n" +
                        "\n" +
                        "~~~\n" +
                        "define foobar() {\n" +
                        "    print \"Welcome to flavor country!\";\n" +
                        "}\n" +
                        "~~~\n" +
                        "\n" +
                        "(which makes copying & pasting easier). You can optionally mark the\n" +
                        "delimited block for Pandoc to syntax highlight it:\n" +
                        "\n" +
                        "~~~python\n" +
                        "import time\n" +
                        "# Quick, count to ten!\n" +
                        "for i in range(10):\n" +
                        "    # (but not *too* quick)\n" +
                        "    time.sleep(0.5)\n" +
                        "    print i\n" +
                        "~~~\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "### An h3 header ###"
        );
    }
}
