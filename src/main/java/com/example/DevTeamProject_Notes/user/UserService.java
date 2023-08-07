package com.example.DevTeamProject_Notes.user;

import com.example.DevTeamProject_Notes.security.AccountController;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void save(User user) {
        String loginInLowerCase = user.getLogin().toLowerCase();
        if (userRepository.existsByLogin(loginInLowerCase)) {
            throw new DuplicateKeyException("User with this login already exists");
        }
        user.setLogin(loginInLowerCase);
        user.setRole(Role.ROLE_USER);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void validateUser(User user, BindingResult result, AccountController accountController) {
        if (userRepository.existsByLogin(user.getLogin().toLowerCase())) {
            result.rejectValue("login", null,
                    "User with this login already exists");
        }
    }
}
