package com.shockn745.data.blogpost.file;

import com.shockn745.domain.application.driven.BlogPostRepository;
import com.shockn745.domain.application.driving.dto.BlogPostDTO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Implementation of the {@link BlogPostRepository} base on files.
 *
 * CONVENTION:
 *
 * (line #) <-> (role)
 * 1 <-> Title
 * 2 <-> Tags --> separated with spaces
 * 3 <-> date --> SimpleDateFormat: yyyy-MM-dd
 *
 * @author Kempenich Florian
 */
public class InFileBlogPostRepositoryImpl implements BlogPostRepository {

    private final Pattern idPattern = Pattern.compile("(\\d+).md");
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
        List<String> tags = parseTags(lines.get(1));
        LocalDate date = parseDate(lines.get(2));
        return BlogPostDTO.make(title, makeContent(lines), extractId(file), date, tags);
    }

    private LocalDate parseDate(String dateString) {
        return LocalDate.parse(dateString);
    }

    private List<String> parseTags(String tagsString) {
        String[] tags = tagsString.split(" ");
        return Arrays.asList(tags);
    }

    private String makeContent(List<String> lines) {
        StringBuilder content = new StringBuilder("");
        int startIndex = getNumberParameterLines();
        for (int i = startIndex; i < lines.size(); i++) {
            content.append(lines.get(i))
                    .append("\n");
        }
        return content.toString();
    }

    /**
     * Return the amount of parameter lines to ignore when parsing the content.
     */
    private int getNumberParameterLines() {
        return 3;
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

            files
                    .filter(this::isValidBlogFile)
                    .forEach(path -> {
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

    private boolean isValidBlogFile(Path file) {
        return file.getFileName().toString().endsWith("md");
    }
}
