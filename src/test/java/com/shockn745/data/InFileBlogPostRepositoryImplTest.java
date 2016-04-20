package com.shockn745.data;

import com.shockn745.domain.application.driven.BlogPostRepository;
import com.shockn745.domain.application.driving.dto.BlogPostDTO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Kempenich Florian
 */
public class InFileBlogPostRepositoryImplTest {

    private Long id1 = 1L;
    private Long id2 = 643274238L;
    private long wrongId = 232345;
    private BlogPostRepository repository;
    private Path tempDir;
    private Path tempFile1;
    private BlogPostDTO post1;
    private Path tempFile2;
    private BlogPostDTO post2;

    @Before
    public void setUp() throws Exception {
        //Write temp file
        tempDir = Files.createTempDirectory(null);
        tempFile1 = tempDir.resolve(makeFileName(id1));
        tempFile2 = tempDir.resolve(makeFileName(id2));

        post1 = writeLinesAndReturnCorrespondingBlogPostDTO(tempFile1, id1,
                "This is the title!",
                "Hello how are you doing ?",
                "My name is giorgos, and I'm the best greek guy on Earth",
                "This is a 3rd line just for test",
                "And now let's put some \"weird\" characters %30 $$ ## and html <h1>HI</h1>"
        );

        post2 = writeLinesAndReturnCorrespondingBlogPostDTO(tempFile2, id2,
                "This is the second file Title",
                "Again, this is now the text",
                "Nothing new here, just some random text"
        );

        repository = new InFileBlogPostRepositoryImpl(tempDir);
    }

    private static String makeFileName(long id1) {
        return "blog-post-" + id1 + ".txt";
    }

    private BlogPostDTO writeLinesAndReturnCorrespondingBlogPostDTO(Path file, Long id, String... lines) {
        List<String> toWrite = Arrays.asList(lines);
        try {
            Files.write(file, toWrite);
            return makeBlogPostDTO(id, toWrite);
        } catch (IOException e) {
            e.printStackTrace();
            return BlogPostDTO.EMPTY;
        }
    }

    private BlogPostDTO makeBlogPostDTO(Long id, List<String> toWrite) {
        String title = toWrite.get(0);

        StringBuilder content = new StringBuilder("");
        for (int i = 1; i < toWrite.size(); i++) {
            content.append(toWrite.get(i))
                    .append("\n");
        }

        return BlogPostDTO.make(
                title,
                content.toString(),
                id
        );
    }

    @Test
    public void savePost_forNow_doNothing_returnEmptyBlogPost() throws Exception {
        BlogPostDTO blogPost1 = repository.save(BlogPostDTO.make("title", "this is the post text", 324));
        BlogPostDTO blogPost2 = repository.save(null);

        assertEquals(BlogPostDTO.EMPTY, blogPost1);
        assertEquals(BlogPostDTO.EMPTY, blogPost2);
    }

    @Test
    public void getPost_wrongId_returnEmptyBlogPost() throws Exception {
        assertEquals(BlogPostDTO.EMPTY, repository.get(wrongId));
    }

    @Test
    public void getPost_existingId_returnPostFromFile() throws Exception {
        BlogPostDTO postFromRepo = repository.get(id1);
        assertEquals(post1, postFromRepo);
    }

    @Test
    public void getAllPost() throws Exception {
        List<BlogPostDTO> allPosts = repository.getAll();

        List<BlogPostDTO> expected = new ArrayList<>();
        expected.add(post1);
        expected.add(post2);

        assertEquals(expected, allPosts);
    }

    @After
    public void tearDown() throws Exception {
        try {
            Files.delete(tempFile2);
            Files.delete(tempFile1);
            Files.delete(tempDir);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}