package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private CredentialsService credentialsService;
    @Autowired
    private FileService fileService;
    @Autowired
    private NoteService noteService;
    @Autowired
    private UsersService usersService;


    @GetMapping()
    public String homeView(Authentication authentication, Model model, HttpSession session) {
        Users user = usersService.getUsers(authentication.getName());
        if(user == null || session.getAttribute("loggedOut") != null){
            session.setAttribute("signupSuccess" , false);
            model.addAttribute("otherError", true);
            return "result";
        }
        Integer userid = user.getUserid();
        model.addAttribute("file",fileService.getUserFiles(userid));
        List<Credentials> credentialsList = credentialsService.getAllCredentials(userid);
//        credentialsList.forEach(Credentials::decodePassword);
        model.addAttribute("credential",credentialsList);
        model.addAttribute("note",noteService.getNotes(userid));
        return "home";
    }


}

