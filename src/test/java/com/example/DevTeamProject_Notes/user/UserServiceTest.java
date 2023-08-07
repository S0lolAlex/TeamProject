package com.example.DevTeamProject_Notes.user;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Mock
    private BindingResult result;

    @Test
    void save() {
        User user = getUser();
        when(userRepository.save(user)).thenReturn(user);
        when(bCryptPasswordEncoder.encode(user.getPassword())).thenReturn("hash");
        userService.save(user);

        verify(userRepository, times(1)).save(user);
        assertNotEquals(user.getPassword(), "qwerty");
        assertEquals(user.getPassword(), "hash");
    }

    @Test
    void validateUser() {
        User user = getUser();
        String login = user.getLogin().toLowerCase();
        when(userRepository.existsByLogin(login)).thenReturn(true);
        userService.validateUser(user, result, null);

        verify(userRepository, times(1)).existsByLogin(login);
        verify(result, times(1)).rejectValue("login",
                null, "User with this login already exists");
    }

    private static User getUser() {
        User user = new User();
        user.setLogin("testuser");
        user.setPassword("qwerty");
        user.setRole(Role.ROLE_USER);
        return user;
    }
}
