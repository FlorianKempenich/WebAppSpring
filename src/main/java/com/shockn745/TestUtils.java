package com.shockn745;

import com.shockn745.data.blogpost.jpa.JpaBlogPostRepo;
import com.shockn745.domain.application.driving.dto.BlogPostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kempenich Florian
 */
@Component
public class TestUtils {

    @Autowired
    JpaBlogPostRepo jpaRepo;

    public List<BlogPostDTO> fillDatabaseWithTestData() {

        LocalDate fakeDate = LocalDate.ofEpochDay(5);
        List<String> fakeTags = new ArrayList<>(3);
        fakeTags.add("Architecture");
        fakeTags.add("TDD");
        fakeTags.add("Android");

        List<BlogPostDTO> expected = new ArrayList<>(3);
        expected.add(makePost(
                "Placeholder markdownContent",
                "Hello this is a blog <strong>post</strong> that is quite long. It's about the end of the uni" +
                        "verse, so be prepared if you actually read through. It's going to be " +
                        "long and tough. \n" +
                        "##Ok let's get's started : At first there was 3 type of stuff, bla bla"
        ));
        expected.add(makePost("Fake", "Hello this is the second blog post of the series. It is basically nothing else" +
                " than just, fake text to fill the blanks.\n" +
                "I hope it's not too boring ^_^. Well let's see. At least we have some data to show the design of the " +
                "website.\n" +
                "I'm happy this project is soon to be done :)"));
        expected.add(makePost("Lorem Ipsum", "Contrary to popular belief, Lorem Ipsum is not simply random text. It has" +
                " roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard " +
                "McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure" +
                " Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in" +
                " classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and " +
                "1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC." +
                " This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line " +
                "of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32."));


        expected.add(
                BlogPostDTO.make("Sample with everything",
                                "Let's first start with a short *introduction* to allow the summary to be produced in the main blog post listing page. Ok I **think** this is long enough for now, let's go with a title now.\n" +
                                "\n" +
                                "\n" +
                                "\n" +
                                "#Lorem Ipsum\n" +
                                "\n" +
                                "\n" +
                                "\n" +
                                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis elementum lacinia nisl rutrum pulvinar. Nunc imperdiet nisi at cursus venenatis. Integer ut ultrices justo. Cras luctus, tortor in posuere aliquet, est augue maximus leo, ut ultricies felis neque a purus. Curabitur accumsan gravida varius. Etiam vitae fringilla est. Nunc bibendum metus elit, sodales consectetur dui accumsan ac. Morbi at justo sapien. Donec accumsan ligula dignissim mi auctor scelerisque. Nam sed risus quis tellus pulvinar pellentesque quis at ligula.\n" +
                                "\n" +
                                "\n" +
                                "\n" +
                                "Fusce vel lacinia sapien. Suspendisse facilisis dui eget facilisis mollis. Nunc eget erat ligula. Suspendisse ac tellus quam. Quisque sed nunc quis enim varius scelerisque. Fusce accumsan felis non vestibulum egestas. Cras massa elit, vestibulum sed pharetra ac, dignissim nec nulla. Suspendisse fringilla lorem et dignissim dapibus. Aenean finibus leo mauris. Fusce purus tortor, semper at turpis nec, fringilla vulputate tortor. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Integer finibus fringilla leo, vitae lobortis felis ultricies ut. Pellentesque sed est pretium, sagittis risus mattis, ornare lacus.\n" +
                                "\n" +
                                "\n" +
                                "\n" +
                                "## Subtitle\n" +
                                "\n" +
                                "\n" +
                                "\n" +
                                "Cras tristique lorem quis tortor commodo vulputate. Fusce blandit magna eget lectus dapibus ultricies ut sed magna. In eget mauris nec leo cursus pharetra sit amet nec elit. Phasellus dapibus pharetra aliquam. Sed est odio, iaculis placerat turpis vitae, gravida pellentesque nibh. Cras in erat et arcu tempor feugiat facilisis ac neque. Fusce nec neque ut est aliquet rutrum et sit amet magna. Quisque ut augue sodales, consectetur mauris ac, aliquam odio. Integer eu lacus lacus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec tincidunt tincidunt nisi, a malesuada libero lobortis at. Nam cursus, mi convallis egestas tempus, nunc odio facilisis nunc, vitae commodo magna augue at lorem. Nam sagittis sed ipsum eu viverra.\n" +
                                "\n" +
                                "\n" +
                                "\n" +
                                "### Sub-subtitle\n" +
                                "\n" +
                                "\n" +
                                "\n" +
                                "Praesent posuere lacinia turpis eget efficitur. Cras lectus turpis, cursus id ante ut, sodales varius nibh. Vivamus tellus libero, vulputate sed tincidunt id, pharetra vitae ex. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. \n" +
                                "\n" +
                                "\n" +
                                "\n" +
                                "### Sub-subtitle 2\n" +
                                "\n" +
                                "\n" +
                                "\n" +
                                "Suspendisse et risus vehicula, vulputate felis ac, semper dolor. Aliquam mattis placerat lacus ut iaculis. Fusce eu maximus arcu. In a orci sit amet ex eleifend posuere. Maecenas quis lectus sit amet risus dapibus bibendum sed ut ligula. Vestibulum sapien urna, iaculis quis ligula eget, feugiat molestie felis. Nunc pharetra at enim in laoreet. Ut id mattis lorem, ultricies cursus ipsum. Suspendisse volutpat purus dui, at tincidunt orci convallis vel. Fusce malesuada purus ut justo fringilla sodales.\n" +
                                "\n" +
                                "\n" +
                                "\n" +
                                "## Subtitle 2\n" +
                                "\n" +
                                "\n" +
                                "\n" +
                                "Phasellus scelerisque erat in ante bibendum, et laoreet lorem commodo. Nullam tincidunt sodales cursus. Donec scelerisque porta ex, in pulvinar nulla gravida ut. Cras in facilisis augue, eget maximus justo. Etiam vestibulum nibh nulla, vel condimentum elit pharetra ac. In eget nunc leo. Nullam molestie semper est eu tempor. Aenean accumsan dui eu leo lobortis volutpat. Pellentesque lobortis tortor vel arcu pharetra, nec pretium tortor aliquet. Nulla id cursus lorem. Curabitur pretium eleifend neque, sit amet eleifend est semper vel. In vitae augue eu ligula imperdiet condimentum ac vitae magna. Fusce eu auctor libero. Donec scelerisque, augue vitae consectetur scelerisque.\n" +
                                "\n" +
                                "\n" +
                                "\n" +
                                "#Technical examples\n" +
                                "\n" +
                                "\n" +
                                "\n" +
                                "In this section we'll try to see how the blog magically displays code and pictures\n" +
                                "\n" +
                                "\n" +
                                "\n" +
                                "## Code section\n" +
                                "\n" +
                                "\n" +
                                "\n" +
                                "Now I'm going to write some code and see how it looks on the website.\n" +
                                "\n" +
                                "\n" +
                                "\n" +
                                "###Java\n" +
                                "\n" +
                                "\n" +
                                "\n" +
                                "```java\n" +
                                "public classs Kouglof {\n" +
                                "\n" +
                                "        public static void main(String[] args) {\n" +
                                "\n" +
                                "        System.out.println(\"Hello World :D :D \");\n" +
                                "\n" +
                                "    }\n" +
                                "\n" +
                                "}\n" +
                                "```\n" +
                                "\n" +
                                "\n" +
                                "\n" +
                                "###Scala\n" +
                                "\n" +
                                "\n" +
                                "\n" +
                                "```scala\n" +
                                "class Example(name: String) {\n" +
                                "\n" +
                                "  val field: Option[Int] = None\n" +
                                "\n" +
                                "}\n" +
                                "```\n" +
                                "\n" +
                                "\n" +
                                "\n" +
                                "## Picture section\n" +
                                "\n" +
                                "\n" +
                                "\n" +
                                "In this section some pictures will be displayed. First just a single picture. Then a picture surrounded with Lorem Ipsum text.\n" +
                                "\n" +
                                "\n" +
                                "\n" +
                                "### Single Picture\n" +
                                "\n" +
                                "\n" +
                                "\n" +
                                "PICTURE:space\n" +
                                "\n" +
                                "\n" +
                                "\n" +
                                "### Surrouded by text\n" +
                                "\n" +
                                "\n" +
                                "\n" +
                                "Phasellus scelerisque erat in ante bibendum, et laoreet lorem commodo. Nullam tincidunt sodales cursus. Donec scelerisque porta ex, in pulvinar nulla gravida ut. Cras in facilisis augue, eget maximus justo. Etiam vestibulum nibh nulla, vel condimentum elit pharetra ac. In eget nunc leo.\n" +
                                "\n" +
                                "\n" +
                                "\n" +
                                "PICTURE:space\n" +
                                "\n" +
                                "\n" +
                                "\n" +
                                "Nullam molestie semper est eu tempor. Aenean accumsan dui eu leo lobortis volutpat. Pellentesque lobortis tortor vel arcu pharetra, nec pretium tortor aliquet. Nulla id cursus lorem. Curabitur pretium eleifend neque, sit amet eleifend est semper vel. In vitae augue eu ligula imperdiet condimentum ac vitae magna. Fusce eu auctor libero. Donec scelerisque, augue vitae consectetur scelerisque.\n" +
                                "\n" +
                                "### Now with a real diagram.\n" +
                                "\n" +
                                "Ok so now this is a real diagram. Let's see how that looks :/\n" +
                                "\n" +
                                "PICTURE:test\n" +
                                "\n" +
                                "What do you think ?\n",
                        1000,
                        fakeDate,
                        fakeTags
                )
        );
        for (BlogPostDTO postDTO : expected) {
            BlogPostDTO saved = jpaRepo.save(postDTO);
            postDTO.setId(saved.getId());
        }
        return expected;
    }

    public void eraseDatabase() {
        jpaRepo.deleteAll();
    }

    public static BlogPostDTO makePost(String title, String text) {
        BlogPostDTO dto = new BlogPostDTO();
        dto.setMarkdownPost(text);
        dto.setTitle(title);
        return dto;
    }
}
