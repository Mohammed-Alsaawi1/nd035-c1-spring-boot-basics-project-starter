package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UsersMapper {
    @Select("SELECT * FROM USERS WHERE username = #{username}")
    Users getUsers(String username);

    @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname) VALUES(#{username}, #{salt}, #{password}, #{firstname}, #{lastname})")
    @Options(useGeneratedKeys = true, keyProperty = "userid")
    Integer insert(Users users);

    @Update("UPDATE USERS SET username = #{username}, salt = #{salt}, password = #{password}, firstname = #{firstname}, lastname = #{lastname} WHERE userid = #{userid}")
    void update(Users users);

    @Delete("DELETE FROM USERS WHERE userid = #{userid}")
    void delete(Integer userid);


}
