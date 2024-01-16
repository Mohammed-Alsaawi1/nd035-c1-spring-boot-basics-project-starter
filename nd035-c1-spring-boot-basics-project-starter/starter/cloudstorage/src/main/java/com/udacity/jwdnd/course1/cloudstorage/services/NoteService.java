package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NotesMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UsersMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NoteService {
    @Autowired
    private NotesMapper noteMapper;
    private UsersMapper usersMapper;

    public NoteService(NotesMapper noteMapper, UsersMapper usersMapper) {
        this.noteMapper = noteMapper;
        this.usersMapper = usersMapper;
        ;
    }

    public int createNotes(Notes notes ) {
        return noteMapper.insert(notes);
    }

    public String[] getUserNoteTitle(Integer userId){
        return noteMapper.getUserNoteTitle(userId);
    }

    public int updateNote(Notes notes){
        return noteMapper.update(notes);
    }

    public void deleteNote(Integer noteId){
        noteMapper.delete(noteId);
    }

    public List<Notes> getNotes(Integer userId){
        return noteMapper.getNotes(userId);
    }


}


