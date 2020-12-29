package dev.jarand.archiveapi.imagename.config;

import static java.nio.charset.StandardCharsets.UTF_8;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BucketConfig {

  @Bean
  public Bucket bucket(
      @Value("${firebase.bucketName}") String bucketName,
      @Value("${firebase.credentials}") String credentialsJson) throws IOException {
    final var credentialsStream = new ByteArrayInputStream(credentialsJson.getBytes(UTF_8));
    final var options =
        FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(credentialsStream))
            .setStorageBucket(bucketName)
            .build();
    FirebaseApp.initializeApp(options);
    return StorageClient.getInstance().bucket();
  }
}
