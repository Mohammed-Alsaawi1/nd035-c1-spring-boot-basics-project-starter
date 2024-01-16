package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.security.SecureRandom;

@Controller
@RequestMapping("/credentials")
public class CredentialsController {

    @Autowired
    private UsersService userService;

    @Autowired
    private CredentialsService credentialsService;

    @Autowired
    private EncryptionService encryptionService;
    private Credentials credentials;

    SecureRandom random = new SecureRandom();


    @PostMapping
    public String addNewCredentials(@RequestParam("credentialId") Integer credentialId,
                                    @RequestParam("url") String url,
                                    @RequestParam("username") String username,
                                    @RequestParam("password") String password,
                                    Model model, Authentication authentication) {


        Integer userid = userService.getUsers(authentication.getName()).getUserid();
        String key = credentialsService.generateKey();

        credentials = new Credentials();
        credentials.setUrl(url);
        credentials.setKey(key);
        credentials.setusername(username);
        credentials.setUserid(userid);
        credentials.setpassword(encryptionService.encryptValue(password, key));
        credentials.setDeccodingpassword(password);

//        credentials.setDeccodingpassword(encryptionService.decryptValue(credentials.getpassword(), key).toString());

        if (credentialId != null && credentialId != 0 && credentialId != -1) {
            credentials.setcredentialId(credentialId);
            credentialsService.updateCredentials(credentials);
            model.addAttribute("success", true);
        } else if (!StringUtils.isEmptyOrWhitespace(username)) {
            credentialsService.createCredentials(credentials);
            model.addAttribute("success", true);
        } else {
            model.addAttribute("error", true);
        }
        return "result";
    }

    @GetMapping(value = {"/delete/{credentialId}"})
    private String deleteCredentials(@PathVariable(name = "credentialId") Integer credentialId) {
        credentialsService.deleteCredentials(credentialId);
        return "redirect:/home";
    }


}
