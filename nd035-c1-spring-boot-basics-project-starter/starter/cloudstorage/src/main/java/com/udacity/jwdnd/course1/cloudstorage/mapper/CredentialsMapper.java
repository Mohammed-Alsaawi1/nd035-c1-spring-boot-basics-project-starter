package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialsMapper {


    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userid}")
    List<Credentials> getAllCredentials(Integer userid);

    @Insert("INSERT INTO CREDENTIALS ( url, username, key , password, userid) VALUES( #{url} ,#{username}, #{key} ,#{password}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    Integer insert(Credentials credentials);

    @Update("UPDATE CREDENTIALS SET credentialId = #{credentialId}, url = #{url} ,username = #{username}, password = #{password}, key = #{key} , userid = #{userid} WHERE credentialId = #{credentialId}")
    Integer update(Credentials credentials);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialId = #{credentialId}")
    void delete(Integer credentialId);

    @Select("SELECT username FROM CREDENTIALS WHERE userid = #{userId}")
    String[] getUserCredentials(Integer userId);




}
