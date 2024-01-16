package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileService fileService;

    @Autowired
    private UsersService userService;

    private Files file;

    public FileController(FileService fileService, NoteService noteService, UsersService userService, EncryptionService encryptionService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @PostMapping
    public String handleFileUploadFile(@RequestParam("fileUpload") MultipartFile fileUpload, Model model, Authentication authentication) throws IOException {
        Integer userid = userService.getUsers(authentication.getName()).getUserid();
        file = new Files();
        System.out.println("-------------------------");
try {
    InputStream fis = fileUpload.getInputStream();
    file.setFilename(fileUpload.getOriginalFilename());
    file.setContenttype(fileUpload.getContentType());
    file.setFiledata(fileUpload.getBytes());
    file.setFilesize(String.valueOf(fileUpload.getSize()));
    file.setUserid(userid);

    List<Files> files = fileService.getUserFiles(userid);
    if (files.stream()
            .anyMatch(var ->
                    var.getFilename()
                            .equals(file.getFilename()))) {
        model.addAttribute("error", true);
        return "result";
    }

    if (file.getFilename() != null && !file.getFilename().equals(" ") && !file.getFilename().equals("")) {
        fileService.insertFile(file);
        model.addAttribute("files", files);
        model.addAttribute("success", true);
    } else {
        model.addAttribute("error", true);
    }
}catch (Exception e){
    model.addAttribute("error", true);
}
        return "result";

    }

    @GetMapping(value = {"/download/{filename}"})
    public ResponseEntity<byte[]> downloadFile(@PathVariable(name = "filename") String filename,
                                               HttpServletResponse response, HttpServletRequest request) {
        Files file = fileService.getFile(filename);
        byte[] fileContents = file.getFiledata();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType(file.getContenttype()));
        String fileName = file.getFilename();
        httpHeaders.setContentDispositionFormData(fileName, fileName);
        httpHeaders.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> serverResponse = new ResponseEntity<byte[]>(fileContents, httpHeaders, HttpStatus.OK);
        return serverResponse;
    }

    @GetMapping(value = {"/delete/{filename}"})
    private String deleteFile(@PathVariable(name = "filename") String filename, RedirectAttributes redirectAttributes){
        fileService.deleteFile(filename);
        redirectAttributes.addFlashAttribute("tab", "nav-files-tab");
        return "redirect:/home";
    }


}
