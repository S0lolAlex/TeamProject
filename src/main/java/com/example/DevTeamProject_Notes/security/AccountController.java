package com.example.DevTeamProject_Notes.security;

import com.example.DevTeamProject_Notes.user.User;
import com.example.DevTeamProject_Notes.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AccountController {
    private final UserService userService;

    @GetMapping("")
    public String beginning(@AuthenticationPrincipal CustomUserDetails loggedUser) {
        return "redirect:/note/list";
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "auth/register";
    }

    @PostMapping("/register/save")
    public String registration(@Validated @ModelAttribute("user") User user,
                               BindingResult result,
                               Model model) {

        userService.validateUser(user, result, this);

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "auth/register";
        }

        userService.save(user);
        return "redirect:/login";
    }
}