package com.shockn745.data;

import com.shockn745.domain.application.driven.SecretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Kempenich Florian
 */
@Component
public class InFileSecretRepository implements SecretRepository {

    private final String secretStoreFilePath;

    @Autowired
    public InFileSecretRepository(@Qualifier("secret_store_file_path") String secretStoreFilePath) {
        this.secretStoreFilePath = secretStoreFilePath;
    }

    @Override
    public String getGiorgosSecret() {
        try {
            return getGiorgosFromFile();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private String getGiorgosFromFile() throws IOException {
        File file = new File(secretStoreFilePath);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String secret = reader.readLine();
        reader.close();
        return secret;
    }
}
