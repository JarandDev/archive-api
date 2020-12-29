package dev.jarand.archiveapi.imagename.service;

import static com.google.cloud.storage.Storage.BlobListOption.prefix;

import com.google.cloud.storage.Bucket;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ImageNameService {

  private static final Logger logger = LoggerFactory.getLogger(ImageNameService.class);

  private final Bucket bucket;

  public ImageNameService(Bucket bucket) {
    this.bucket = bucket;
  }

  public List<String> getImagesNames(String year) {
    logger.debug("Getting image names for year {}", year);
    final var folder = String.format("bread-bags/%s/", year);
    final var blobs = new ArrayList<String>();
    for (final var blob : bucket.list(prefix(folder)).iterateAll()) {
      blobs.add(blob.getName().replace(folder, ""));
    }
    final var imageNames = blobs.subList(1, blobs.size());
    logger.info("Got {} image names for year {}", imageNames.size(), year);
    return imageNames;
  }
}
