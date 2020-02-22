package com.testing.controller;

import com.testing.domain.Role;
import com.testing.domain.User;
import com.testing.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private UserServiceImpl userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/main")
    public String main(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user.getUsername());

        if (user.getRole() == Role.ADMIN) {
            model.addAttribute("admin", user);
        }

        return "main";
    }

    @GetMapping("/signUp")
    public String signUp(Model model) {
        model.addAttribute("title", "Sign Up");
        model.addAttribute("h2", "Sign Up");
        model.addAttribute("signUp", true);
        model.addAttribute("action", "/signUp");
        return "auth";
    }

    @PostMapping("/signUp")
    public String signUp(@RequestParam String username, @RequestParam String password, Model model) {
        model.addAttribute("title", "Sign Up");
        model.addAttribute("h2", "Sign Up");
        model.addAttribute("signUp", true);
        model.addAttribute("action", "/signUp");

        boolean checkResult = userCheck(username, password, model);

        if (!checkResult) {
            return "auth";
        }

        User userFromDb = userService.findByName(username);

        if (userFromDb != null) {
            model.addAttribute("message", "This name is already taken, please choose another!");
            return "auth";
        }

        userService.save(new User(username, passwordEncoder.encode(password), Role.USER));

        return "redirect:/main";
    }

    @GetMapping("/signIn")
    public String signIn(Model model) {
        model.addAttribute("title", "Sign In");
        model.addAttribute("h2", "Sign In");
        model.addAttribute("signIn", true);
        model.addAttribute("action", "/signIn");
        return "auth";
    }

    private boolean userCheck(String username, String password, Model model) {
        if (username.isEmpty()) {
            model.addAttribute("message", "Username cannot be empty!");
            return false;
        } else if (password.isEmpty()) {
            model.addAttribute("emptyPass", "Password cannot be empty!");
            return false;
        }
        return true;
    }
}
