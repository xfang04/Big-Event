package com.xfang.mapper;

import com.xfang.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
  @Select("select * from user where username = #{username}")
  User findByUsername(String username);

  @Insert(
      "insert into user(username, password, create_time, update_time)"
          + "values(#{username}, #{md5Password}, now(), now())")
  void add(String username, String md5Password);
}
