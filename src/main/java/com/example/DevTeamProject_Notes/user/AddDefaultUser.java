package com.example.DevTeamProject_Notes.user;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddDefaultUser {

    private final UserService userService;
    private final UserRepository userRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void appReady() {
        if (userRepository.findByLogin("admin") == null) {
            addAdminUser();
        }
    }

    private void addAdminUser() {
        User adminUser = new User();
        adminUser.setLogin("admin");
        adminUser.setPassword("super_secret_password");
        adminUser.setRole(Role.ROLE_USER);
        userService.save(adminUser);
    }
}
