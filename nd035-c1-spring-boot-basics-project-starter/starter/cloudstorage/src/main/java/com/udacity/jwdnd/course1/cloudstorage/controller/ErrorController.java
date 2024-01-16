package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/error")
public class ErrorController {


    @GetMapping
    public String erroraatatattat(){
        return "result?error=true";
    }

}
