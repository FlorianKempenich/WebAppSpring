package com.shockn745.data;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import static org.junit.Assert.assertEquals;

/**
 * @author Kempenich Florian
 */
public class InFileSecretRepositoryTest {

    private SecretRepository secretRepository;
    private final String giorgosSecret = "giorgosSecret";

    @Before
    public void setUp() throws Exception {
        String path = "aaaa_temp_file";
        writeSecretToFile(path);

        secretRepository = new InFileSecretRepository(path);
    }

    private void writeSecretToFile(String path) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(path);
        printWriter.print(giorgosSecret);
        printWriter.close();
    }

    @Test
    public void getGiorgosSecret() throws Exception {
        assertEquals(giorgosSecret, secretRepository.getGiorgosSecret());
    }

}