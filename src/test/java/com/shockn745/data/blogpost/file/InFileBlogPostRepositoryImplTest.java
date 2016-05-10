package com.shockn745.data.blogpost.file;

import com.shockn745.domain.application.driven.BlogPostRepository;
import com.shockn745.domain.application.driving.dto.BlogPostDTO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * @author Kempenich Florian
 */
public class InFileBlogPostRepositoryImplTest {

    private int id1 = 1;
    private int id2 = 643274238;
    private int wrongId = 232345;
    private BlogPostRepository repository;
    private Path tempDir;
    private Path tempFile1;
    private BlogPostDTO post1;
    private Path tempFile2;
    private BlogPostDTO post2;

    private List<Path> additionalTempFilesToDelete = new LinkedList<>();

    @Before
    public void setUp() throws Exception {
        //Write temp file
        tempDir = Files.createTempDirectory(null);
        tempFile1 = tempDir.resolve(makeFileName(id1));
        tempFile2 = tempDir.resolve(makeFileName(id2));

        List<String> tags1 = new ArrayList<>();
        tags1.add("TDD");
        tags1.add("Architecture");
        List<String> tags2 = new ArrayList<>();
        tags2.add("Android");
        tags2.add("DDD");


        post1 = writeLinesAndReturnCorrespondingBlogPostDTO(tempFile1, id1,
                LocalDate.of(2016,5,9),
                tags1,
                "This is the title!",
                "TDD Architecture", //tags2
                "2016-05-09",
                "Hello how are you doing ?",
                "My name is giorgos, and I'm the best greek guy on Earth",
                "This is a 3rd line just for test",
                "And now let's put some \"weird\" characters %30 $$ ## and html <h1>HI</h1>"
        );

        post2 = writeLinesAndReturnCorrespondingBlogPostDTO(tempFile2, id2,
                LocalDate.of(2020,8,6),
                tags2,
                "This is the second file Title",
                "Android DDD", //tags2
                "2020-08-06",
                "Again, this is now the text",
                "Nothing new here, just some random text"
        );

        repository = new InFileBlogPostRepositoryImpl(tempDir);
    }

    private static String makeFileName(long id1) {
        return "blog-markdownContent-" + id1 + ".md";
    }

    private BlogPostDTO writeLinesAndReturnCorrespondingBlogPostDTO(Path file, int id, LocalDate date,
                                                                    List<String> tags,  String... lines) {
        List<String> toWrite = Arrays.asList(lines);
        try {
            Files.write(file, toWrite);
            return makeBlogPostDTO(id, toWrite, date, tags);
        } catch (IOException e) {
            e.printStackTrace();
            return BlogPostDTO.EMPTY;
        }
    }

    private BlogPostDTO makeBlogPostDTO(int id, List<String> toWrite, LocalDate date, List<String> tags) {
        String title = toWrite.get(0);

        StringBuilder content = new StringBuilder("");
        for (int i = 1; i < toWrite.size(); i++) {
            content.append(toWrite.get(i))
                    .append("\n");
        }

        return BlogPostDTO.make(
                title,
                content.toString(),
                id,
                date,
                tags
        );
    }

    @Test
    public void savePost_forNow_doNothing_returnEmptyBlogPost() throws Exception {
        BlogPostDTO blogPost1 = repository.save(BlogPostDTO.make("title", "this is the markdownContent text", 324, LocalDate.MIN, new ArrayList<>()));
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

    /**
     * This test checks that the id is equal the last number in the file name. (Convention)
     * @throws Exception
     */
    @Test
    public void test_id_onlyDependsOnLastNumber() throws Exception {
        String fileName = "sdhsadf23.md";
        Path tempFile = tempDir.resolve(fileName);
        BlogPostDTO tempPost = writeLinesAndReturnCorrespondingBlogPostDTO(tempFile, 23,
                LocalDate.of(2016,5,9),
                new ArrayList<>(),
                "Title",
                " ",
                "2016-05-09",
                "Content",
                "Content line 1"
        );
        String fileName2 = "hello-57.md";
        Path tempFile2 = tempDir.resolve(fileName2);
        BlogPostDTO tempPost2 = writeLinesAndReturnCorrespondingBlogPostDTO(tempFile2, 57,
                LocalDate.of(2016,5,9),
                new ArrayList<>(),
                "Title",
                " ",
                "2016-05-09",
                "Content",
                "Content line 1"
        );

        String fileName3 = "h232--sdfdsah33.md";
        Path tempFile3 = tempDir.resolve(fileName3);
        BlogPostDTO tempPost3 = writeLinesAndReturnCorrespondingBlogPostDTO(tempFile3, 33,
                LocalDate.of(2016,5,9),
                new ArrayList<>(),
                "Title",
                " ",
                "2016-05-09",
                "Content",
                "Content line 1"
        );
        additionalTempFilesToDelete.add(tempFile);
        additionalTempFilesToDelete.add(tempFile2);
        additionalTempFilesToDelete.add(tempFile3);


        assertEquals(tempPost.getId(), repository.get(23).getId());
        assertEquals(tempPost2.getId(), repository.get(57).getId());
        assertEquals(tempPost3.getId(), repository.get(33).getId());
    }

    @Test (expected = TwoPostWithSameIdException.class)
    public void twoPostWithSameId_throwException() throws Exception {
        // Save 2 files with same id
        String fileName = "sdhsadf23.md";
        Path tempFile = tempDir.resolve(fileName);
        writeLinesAndReturnCorrespondingBlogPostDTO(tempFile, 23,
                LocalDate.of(2016,5,9),
                new ArrayList<>(),
                "Title",
                " ",
                "2016-05-09",
                "Content",
                "Content line 1"
        );
        String fileName2 = "hello-23.md";
        Path tempFile2 = tempDir.resolve(fileName2);
        writeLinesAndReturnCorrespondingBlogPostDTO(tempFile2, 23,
                LocalDate.of(2016,5,9),
                new ArrayList<>(),
                "Title",
                " ",
                "2016-05-09",
                "Content",
                "Content line 1"
        );
        additionalTempFilesToDelete.add(tempFile);
        additionalTempFilesToDelete.add(tempFile2);

        repository.get(23);
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

            for (Path file : additionalTempFilesToDelete) {
                Files.delete(file);
            }
            additionalTempFilesToDelete.clear();

            Files.delete(tempDir);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}