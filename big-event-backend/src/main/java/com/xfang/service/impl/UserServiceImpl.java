package com.xfang.service.impl;

import com.xfang.mapper.UserMapper;
import com.xfang.pojo.User;
import com.xfang.service.UserService;
import com.xfang.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  @Autowired private UserMapper userMapper;

  @Override
  public User findByUsername(String username) {
    return userMapper.findByUsername(username);
  }

  @Override
  public void register(String username, String password) {
    String md5Password = Md5Util.getMD5String(password);
    userMapper.add(username, md5Password);
  }
}
