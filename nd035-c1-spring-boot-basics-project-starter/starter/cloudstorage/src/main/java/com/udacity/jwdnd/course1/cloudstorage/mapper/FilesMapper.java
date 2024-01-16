package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FilesMapper {


    @Select("SELECT * FROM Files WHERE filename = #{filename}")
    Files findByName(String filename);

    @Insert("INSERT INTO FILES ( filename, contenttype, filesize, userid, filedata) VALUES( #{filename}, #{contenttype}, #{filesize}, #{userid}, #{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    Integer insert(Files files);

    @Update("UPDATE FILES SET fileId = #{fileId}, filename = #{filename}, contenttype = #{contenttype}, filesize = #{filesize}, userid = #{userid}, filedata = #{filedata}, url = #{url} WHERE fileId = #{fileId}")
    void update(Files files);

    @Delete("DELETE FROM FILES WHERE filename = #{filename}")
    void delete(String filename);

    @Select("SELECT filename FROM FILES WHERE userid = #{userId}")
    String[] getUserFiles(Integer userId);

    @Select("SELECT * FROM FILES WHERE userid = #{userId}")
    List<Files> getUserFiles1(Integer userId);


}
