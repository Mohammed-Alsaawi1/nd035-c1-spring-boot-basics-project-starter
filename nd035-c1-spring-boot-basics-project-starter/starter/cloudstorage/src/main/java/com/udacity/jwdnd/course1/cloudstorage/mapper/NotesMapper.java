package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NotesMapper {

    @Select("SELECT * FROM NOTES WHERE userid = #{userid}")
    List<Notes> getNotes(Integer userid);

    @Insert("INSERT INTO NOTES ( noteTitle, noteDescription, userid) VALUES( #{noteTitle}, #{noteDescription}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    Integer insert(Notes notes);

    @Update("UPDATE NOTES SET noteTitle = #{noteTitle}, noteDescription = #{noteDescription}, userid = #{userid} WHERE noteId = #{noteId}")
    Integer update(Notes notes);



    @Select("SELECT noteTitle FROM NOTES WHERE userid = #{userId}")
    String[] getUserNoteTitle(Integer userId);

    @Delete("DELETE FROM NOTES WHERE noteId = #{noteId}")
    void delete(Integer noteId);

}
