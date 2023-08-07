package com.example.DevTeamProject_Notes.security;

import com.example.DevTeamProject_Notes.user.Role;
import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithMockCustomUserSecurityContextFactory.class)
public @interface WithMockCustomUser {

    long id() default 100L;

    String login() default "testuser";

    Role role() default Role.ROLE_USER;
}
