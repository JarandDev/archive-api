package dev.jarand.archiveapi.imagename.rest;

import dev.jarand.archiveapi.imagename.service.ImageNameService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("image-name")
public class ImageNameController {

  private final ImageNameService imageNameService;

  public ImageNameController(ImageNameService imageNameService) {
    this.imageNameService = imageNameService;
  }

  @GetMapping
  public ResponseEntity<List<String>> getImageNames(@RequestParam String year) {
    return ResponseEntity.ok(imageNameService.getImagesNames(year));
  }
}
