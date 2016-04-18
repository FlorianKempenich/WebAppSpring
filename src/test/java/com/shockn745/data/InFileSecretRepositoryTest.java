package com.shockn745.data;

import com.shockn745.domain.application.driven.SecretRepository;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

/**
 * @author Kempenich Florian
 */
public class InFileSecretRepositoryTest {

    private SecretRepository secretRepository;
    private static final String MOCK_SECRET = "giorgos";
    private static final String MOCK_FILE_PATH = "aaaa_temp_file";

    @BeforeClass
    public static void setUpClass() throws Exception {
        writeMockSecretToFile();
    }

    @Before
    public void setUp() throws Exception {
        secretRepository = new InFileSecretRepository(MOCK_FILE_PATH);

    }

    private static void writeMockSecretToFile() throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(MOCK_FILE_PATH);
        printWriter.print(MOCK_SECRET);
        printWriter.close();
    }

    @Test
    public void getGiorgosSecret() throws Exception {
        assertEquals(MOCK_SECRET, secretRepository.getGiorgosSecret());
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        try {
            Files.delete(Paths.get(MOCK_FILE_PATH));
        } catch (Exception e) {
            e.printStackTrace();
            // File couldn't be deleted : do nothing
        }
    }
}