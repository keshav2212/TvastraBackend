package com.tvastra.users;

import com.tvastra.TvastraApplication;
import com.tvastra.exceptions.UserNameAlreadyExistsException;
import com.tvastra.user.User;
import com.tvastra.user.UserDTO;
import com.tvastra.user.UserPrincipleService;
import com.tvastra.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = TvastraApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserPrincipleServiceTest {
    @MockBean
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserPrincipleService userPrincipleService;

    @Autowired
    public UserPrincipleServiceTest(PasswordEncoder passwordEncoder, UserPrincipleService userPrincipleService) {
        this.passwordEncoder = passwordEncoder;
        this.userPrincipleService = userPrincipleService;
    }

    @Test
    void should_add_user() throws UserNameAlreadyExistsException {
        UserDTO userDTO = new UserDTO("swetha", "swetha", "swetha", "s@gmail.com", "USER");
        String password = passwordEncoder.encode(userDTO.getPassword());
        User expectedUser = new User(userDTO.getUsername(), password, userDTO.getRole());
        when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(expectedUser);

        User user = userPrincipleService.saveUser(userDTO);

        assertEquals(expectedUser, user);
    }
}

