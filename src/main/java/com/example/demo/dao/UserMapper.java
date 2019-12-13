package com.example.demo.dao;

import com.example.demo.domain.UserDemo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @version 1.0  on 2019/12/11 16:03
 */
@Mapper
public interface UserMapper {

    @Select("select * from user where id = #{id}")
    UserDemo selectById(Integer id);

    @Select("select * from user")
    List<UserDemo> selectAll();

    void insertUser(@Param("user") UserDemo user);
}
