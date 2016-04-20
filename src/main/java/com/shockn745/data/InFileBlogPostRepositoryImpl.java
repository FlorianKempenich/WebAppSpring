package com.shockn745.data;

import com.shockn745.domain.application.driven.BlogPostRepository;
import com.shockn745.domain.application.driving.dto.BlogPostDTO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Kempenich Florian
 */
public class InFileBlogPostRepositoryImpl implements BlogPostRepository {

    private final Path directory;

    public InFileBlogPostRepositoryImpl(Path directory) {
        this.directory = directory;
    }

    @Override
    public BlogPostDTO save(BlogPostDTO toSave) {
        return BlogPostDTO.EMPTY;
    }

    /**
     * Returns a blog post stored in a file at : blog-post-ID.txt in the given directory.
     * By convention the first line is considered the title of the blog post.
     *
     * @param id Id of the blog post
     * @return BlogPost for given id
     */
    @Override
    public BlogPostDTO get(long id) {
        Path file = getPath(id);
        try {
            return getPost(file);
        } catch (NoSuchFileException e) {
            // Normal : id inexistant
            return BlogPostDTO.EMPTY;
        } catch (IOException e) {
            e.printStackTrace();
            return BlogPostDTO.EMPTY;
        }
    }

    private BlogPostDTO getPost(Path file) throws IOException {
        List<String> lines = Files.readAllLines(file);
        String title = lines.get(0); // CONVENTION
        return BlogPostDTO.make(title, makeContent(lines), extractId(file));
    }

    private String makeContent(List<String> lines) {
        StringBuilder content = new StringBuilder("");
        for (int i = 1; i < lines.size(); i++) {
            content.append(lines.get(i))
                    .append("\n");
        }
        return content.toString();
    }

    private long extractId(Path file) {
        String filename = file.getFileName().toString();

        String withoutExtension = filename.split("[.]")[0];
        String id = withoutExtension.substring(10);
        return Long.parseLong(id);
    }

    private Path getPath(long id) {
        String filename = makeFilename(id);
        return directory.resolve(filename);
    }

    private String makeFilename(long id) {
        return "blog-post-" + id + ".txt";
    }

    @Override
    public List<BlogPostDTO> getAll() {
        try {
            Stream<Path> files = Files.list(directory);

            List<BlogPostDTO> posts = new ArrayList<>();
            files.forEach(path -> {
                try {
                    BlogPostDTO post = getPost(path);
                    posts.add(post);
                } catch (IOException e) {
                    System.err.println("Couldn't find post: " + path);
                }
            });
            return posts;

        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
