package com.example.DevTeamProject_Notes.cms;

import com.example.DevTeamProject_Notes.security.WithMockCustomUser;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class CmsPageControllerTest {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @SneakyThrows
    @WithMockCustomUser
    void about() {
        mvc.perform(get("/about"))
                .andExpect(status().isOk())
                .andExpect(view().name("cms/about"));
    }

    @Test
    @SneakyThrows
    @WithMockCustomUser
    void contact() {
        mvc.perform(get("/contact-us"))
                .andExpect(status().isOk())
                .andExpect(view().name("cms/contact-us"));
    }
}