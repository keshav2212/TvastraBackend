package com.tvastra.gallery;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tvastra.Price.Currency;
import com.tvastra.Price.Price;
import com.tvastra.TvastraApplication;
import com.tvastra.gallery.artwork.Artwork;
import com.tvastra.gallery.artwork.ArtworkInfo;
import com.tvastra.gallery.artwork.ArtworkRepository;
import com.tvastra.gallery.artwork.model.ArtworkDTO;
import com.tvastra.gallery.artwork.model.ArtworkInfoDTO;
import com.tvastra.gallery.category.Category;
import com.tvastra.user.Role;
import com.tvastra.user.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.io.FileInputStream;

import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(classes = TvastraApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class GalleryControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GalleryRepository galleryRepository;

    @Autowired
    private ArtworkRepository artworkRepository;

    @BeforeEach
    public void before() {
        galleryRepository.deleteAll();
        artworkRepository.deleteAll();
    }

    @AfterEach
    public void after() {
        galleryRepository.deleteAll();
        artworkRepository.deleteAll();
    }

    @Test
    public void shouldBeAbleToPostGallerySuccessfully() throws Exception {
        mockMvc.perform(post("/gallery/create-gallery")
                        .with(user("user"))
                        .param("gallery", "test-gallery"))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void shouldBeAbleToPostArtworksSuccessfully() throws Exception {
        FileInputStream inputStream = new FileInputStream("src/test/data/image.webp");
        MockMultipartFile uploadFile = new MockMultipartFile("image", "image.webp", "image/webp", inputStream);
        ObjectMapper objectMapper = new ObjectMapper();
        ArtworkDTO artworkDTO = new ArtworkDTO(
                new ArtworkInfoDTO("test_title", "test_description"),
                new Price(222, Currency.RUPEE),
                1L,
                1L,
                "test-user"
        );
        String jsonData = objectMapper.writeValueAsString(artworkDTO);

        mockMvc.perform(multipart("/gallery/upload-your-artwork")
                        .file(uploadFile).with(user("user"))
                        .param("model", jsonData))
                .andExpect(status().isOk())
                .andReturn();

    }


    @Test
    public void shouldBeAbleToGetAllArtworksForAGallery() throws Exception {
        ArtworkInfo info = new ArtworkInfo("test_title", "test_description");
        User addedBy = new User("user", "user", Role.USER);
        Gallery test_gallery = new Gallery("test_gallery");
        artworkRepository.save(new Artwork(info, addedBy, test_gallery, new Price(), "uploads", new Category()));

        mockMvc.perform(get("/gallery/test_gallery")
                        .with(user("user"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].info.title", is("test_title")));
    }

}
