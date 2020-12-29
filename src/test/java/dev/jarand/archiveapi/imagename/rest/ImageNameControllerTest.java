package dev.jarand.archiveapi.imagename.rest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import dev.jarand.archiveapi.ApiTest;
import org.junit.jupiter.api.Test;

class ImageNameControllerTest extends ApiTest {

  @Test
  void GET_image_names_for_year_with_images_should_return_200_and_list_of_image_names() throws Exception {
    get("/image-name?year=1970")
        .andExpect(status().isOk())
        .andExpect(content().json(jsonResponse("get-image-names-year-with-images.json")));
  }

  @Test
  void GET_image_names_for_year_without_images_should_return_200_and_empty_list() throws Exception {
    get("/image-name?year=1900")
        .andExpect(status().isOk())
        .andExpect(content().json(jsonResponse("get-image-names-year-without-images.json")));
  }

  @Test
  void GET_image_names_without_year_should_return_400() throws Exception {
    get("/image-name").andExpect(status().isBadRequest());
  }
}
