package com.shockn745.data.blogpost.file;

import com.shockn745.domain.application.driven.BlogPostRepository;
import com.shockn745.domain.application.driving.dto.BlogPostDTO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Kempenich Florian
 */
public class InFileBlogPostRepositoryImpl implements BlogPostRepository {

    private final Pattern idPattern = Pattern.compile("(\\d+).txt");
    private final Path directory;

    public InFileBlogPostRepositoryImpl(Path directory) {
        this.directory = directory;
    }

    @Override
    public BlogPostDTO save(BlogPostDTO toSave) {
        return BlogPostDTO.EMPTY;
    }

    /**
     * Returns a blog markdownContent stored in a file at : blog-markdownContent-ID.txt in the given directory.
     * By convention the first line is considered the title of the blog markdownContent.
     *
     * @param id Id of the blog markdownContent
     * @return BlogPost for given id
     */
    @Override
    public BlogPostDTO get(int id) {
        try {
            Path file = getPath(id);
            return getPost(file);
        } catch (IdNotFoundException | NoSuchFileException e) {
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

    private Path getPath(long id) throws IdNotFoundException {
        try {
            // Get all files in folder
            Stream<Path> files = Files.list(directory);
            List<Path> fileList = files.collect(Collectors.toList()); // Not used to work with Streams yet

            List<Path> matchingPaths = new LinkedList<>();
            for (Path path : fileList) {
                if (fileMatchesId(id, path)) {
                    matchingPaths.add(path);
                }
            }

            if (matchingPaths.isEmpty()) {
                throw new IdNotFoundException();
            } else if (matchingPaths.size() > 1) {
                throw new TwoPostWithSameIdException();
            } else {
                return matchingPaths.get(0);
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new IdNotFoundException();
        }
    }

    private boolean fileMatchesId(long id, Path path) {
        long fileId = extractId(path);
        return fileId == id;
    }

    private int extractId(Path file) {
        String filename = file.getFileName().toString();
        Matcher matcher = idPattern.matcher(filename);

        if (matcher.find()) {
            String id = matcher.group(1);
            return Integer.parseInt(id);
        } else {
            return -1;
        }
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
                    System.err.println("Couldn't find markdownContent: " + path);
                }
            });
            return posts;

        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
