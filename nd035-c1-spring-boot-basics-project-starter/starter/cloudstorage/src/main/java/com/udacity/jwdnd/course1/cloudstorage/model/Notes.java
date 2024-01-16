package com.udacity.jwdnd.course1.cloudstorage.model;

public class Notes {
    private Integer  noteId;
    private String noteTitle;
    private String noteDescription;
    private Integer userid;

    public Notes(Integer noteId, String noteTitle, String noteDescription, Integer userid) {
        this.noteId = noteId;
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
        this.userid = userid;
    }

    public Notes() {
    }

    public int getnoteId() {
        return noteId;
    }

    public void setnoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getnoteTitle() {
        return noteTitle;
    }

    public void setnoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getnoteDescription() {
        return noteDescription;
    }

    public void setnoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
