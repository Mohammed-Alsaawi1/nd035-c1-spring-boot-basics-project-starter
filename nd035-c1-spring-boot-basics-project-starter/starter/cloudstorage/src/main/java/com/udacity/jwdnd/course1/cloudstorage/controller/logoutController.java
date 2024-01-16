package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

public class logoutController {


@PostMapping("/logout")
    public String logout(/*HttpSession session*/){
  //  session.setAttribute("loggedOut",true);
    return "login";
}



}
