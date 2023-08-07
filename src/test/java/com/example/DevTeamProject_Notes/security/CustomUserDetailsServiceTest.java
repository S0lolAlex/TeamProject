package com.example.DevTeamProject_Notes.security;

import com.example.DevTeamProject_Notes.user.User;
import com.example.DevTeamProject_Notes.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class CustomUserDetailsServiceTest {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @MockBean
    private UserRepository userRepository;

    @Test
    void loadUserByUsername() {
        String userName = "User1";
        User user = new User();
        user.setLogin(userName);
        when(userRepository.findByLogin(userName.toLowerCase())).thenReturn(user);

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(userName);

        assertEquals(userDetails.getUsername(), userName);
    }

    @Test
    void loadUserByUsernameWithError() {
        String userName = "User1";

        when(userRepository.findByLogin(userName)).thenReturn(null);
        UsernameNotFoundException thrown = assertThrows(UsernameNotFoundException.class, () -> {
            customUserDetailsService.loadUserByUsername(userName);
        });

        assertEquals("User not found", thrown.getMessage());
    }
}