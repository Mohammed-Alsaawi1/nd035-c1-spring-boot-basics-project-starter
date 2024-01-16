package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

@Controller
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private NoteService noteService;
    private Notes note;
    @PostMapping
    public String addNewNote(@RequestParam(value = "noteId")Integer noteId,
                             @RequestParam("noteTitle")String noteTitle,
                             @RequestParam("noteDescription")String noteDescription,
                             Model model, Authentication authentication){
        Integer userid = usersService.getUsers(authentication.getName()).getUserid();
        note = new Notes();
        note.setUserid(userid);
        note.setnoteDescription(noteDescription);
        note.setnoteTitle(noteTitle);


        if (noteId != null && noteId !=0 && noteId != -1) {
            note.setnoteId(noteId);
            noteService.updateNote(note);
            model.addAttribute("success", true);
        } else if (!StringUtils.isEmptyOrWhitespace(noteTitle)) {
            noteService.createNotes(note);
            model.addAttribute("success", true);
        } else {
            model.addAttribute("error",  true);
        }
        return "result";
    }



    @GetMapping(value = {"/delete/{noteId}"})
    private String deleteNote(@PathVariable(name = "noteId") Integer noteId) {
        noteService.deleteNote(noteId);
        return "redirect:/home";
    }

}
