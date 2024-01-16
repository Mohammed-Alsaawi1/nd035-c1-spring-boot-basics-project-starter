package com.udacity.jwdnd.course1.cloudstorage.controller;




import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller()
@RequestMapping("/signup")
public class SignupController {

    private final UsersService usersService;

    public SignupController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping()
    public String signupView() {
        return "signup";
    }

    @PostMapping()
    public String signupUser(@ModelAttribute Users user, Model model, HttpSession session) {
        String signupError = null;

        if (!usersService.isUsersnameAvailable(user.getUsername())) {
            signupError = "The username already exists.";
        }

        if (signupError == null) {
            int rowsAdded = usersService.createUsers(user);
            if (rowsAdded < 0) {
                signupError = "There was an error signing you up. Please try again.";
            }
        }

        if (signupError == null) {
            session.setAttribute("signupSuccess", true);
            return "redirect:/login";
        } else {
            model.addAttribute("signupError", signupError);
            return "signup";
        }


    }
}

