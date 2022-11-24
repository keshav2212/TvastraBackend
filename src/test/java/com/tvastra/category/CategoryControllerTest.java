package com.tvastra.category;

import com.tvastra.TvastraApplication;
import com.tvastra.gallery.category.Category;
import com.tvastra.gallery.category.CategoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = TvastraApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void before() {
        categoryRepository.deleteAll();
    }

    @AfterEach
    public void after() {
        categoryRepository.deleteAll();
    }

    @Test
    public void shouldBeAbleToGetAllCategories() throws Exception {
        categoryRepository.save(new Category("test-category1"));
        categoryRepository.save(new Category("test-category2"));

        mockMvc.perform(get("/category")
                        .with(user("user"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", is("test-category1")));
    }

//    @Test
//    public void shouldBeAbleToPostSuccessfully() throws Exception {
//        mockMvc.perform(post("/category")
//                        .with(user("user"))
//                        .with(httpBasic("user", "pass"))
//                        .param("name", "test-category"))
//                .andExpect(status().isOk());
//
//    }


}
