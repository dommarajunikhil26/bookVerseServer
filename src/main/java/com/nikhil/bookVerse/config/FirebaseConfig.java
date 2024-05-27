package com.nikhil.bookVerse.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Logger;

@Configuration
public class FirebaseConfig {

    @Autowired
    private Environment env;

    @Bean
    public FirebaseApp initializeFirebase() throws IOException {
        String serviceAccountKeyPath = env.getProperty("FIREBASE_SERVICE_KEY_PATH");

        FileInputStream serviceAccount =
                new FileInputStream(serviceAccountKeyPath);

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        return FirebaseApp.initializeApp(options);
    }
}
