package com.shopex.users;

import com.shopex.ShopexApplication;
import com.shopex.user.Role;
import com.shopex.user.User;
import com.shopex.user.UserRepository;
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

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ShopexApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserControllerIntegrationTest {
    private MockMvc mockMvc;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserControllerIntegrationTest(MockMvc mockMvc, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.mockMvc = mockMvc;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @BeforeEach
    public void before() {
        userRepository.deleteAll();
    }

    @AfterEach
    public void after() {
        userRepository.deleteAll();
    }

    @Test
    void shouldBeAbleToLoginSuccessfully() throws Exception {
        userRepository.save(new User("test-user", passwordEncoder.encode("password"), Role.USER));
        mockMvc.perform(post("/login")
                        .with(httpBasic("test-user", "password")))
                .andExpect(status().isOk());
    }

    @Test
    void shouldBeAbleToRegisterSuccessfully() throws Exception {
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                "{\"username\":\"test-user\"," +
                                        "\"password\":\"password\"," +
                                        "\"role\":\"ADMIN\"," + "\"emailid\":\"test@gmail.com\"," + "\"name\" : \"user\"}"))
                .andExpect(status().isOk()).andReturn();
    }
}
