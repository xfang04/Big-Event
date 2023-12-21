package com.xfang.service;

import com.xfang.pojo.User;

public interface UserService {

  User findByUsername(String username);

  void register(String username, String password);
}
