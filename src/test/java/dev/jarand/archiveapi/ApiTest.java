package dev.jarand.archiveapi;

import static org.mockito.Mockito.when;

import com.google.cloud.storage.Bucket;
import dev.jarand.archiveapi.imagename.service.ImageNameService;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = {"spring.flyway.enabled=false"})
@AutoConfigureMockMvc
public class ApiTest {

  @Autowired protected MockMvc mockMvc;

  @MockBean protected ImageNameService imageNameService;

  @MockBean protected Bucket bucket;

  @BeforeEach
  void setup() {
    when(imageNameService.getImagesNames("1970")).thenReturn(List.of("1.jpg", "2.jpg"));
  }

  protected ResultActions get(String url) throws Exception {
    return mockMvc.perform(MockMvcRequestBuilders.get(url));
  }

  protected String jsonResponse(String file) {
    return json("/json/response/" + file);
  }

  protected String json(String file) {
    return new BufferedReader(new InputStreamReader(ApiTest.class.getResourceAsStream(file)))
        .lines()
        .collect(Collectors.joining());
  }
}
