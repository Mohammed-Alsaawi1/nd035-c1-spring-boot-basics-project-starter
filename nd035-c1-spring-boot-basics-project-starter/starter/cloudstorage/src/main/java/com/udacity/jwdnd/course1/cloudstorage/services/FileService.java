package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FilesMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UsersMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class FileService {
    @Autowired
   private FilesMapper filesMapper;
    @Autowired
    private UsersMapper usersMapper;

    public FileService(FilesMapper filesMapper, UsersMapper usersMapper) {
        this.filesMapper = filesMapper ;
       this.usersMapper = usersMapper;
    }
    @PostConstruct
    public void postConstruct() {System.out.println("Creating FileService Bean"); }

    public List<Files> getUserFiles(Integer userId){
        return filesMapper.getUserFiles1(userId);
    }

    public Files getFile(String fileName) { return filesMapper.findByName(fileName); }

    public int insertFile(Files file) { return filesMapper.insert(file); }

    public void deleteFile(String fileName) { filesMapper.delete(fileName); }
}





