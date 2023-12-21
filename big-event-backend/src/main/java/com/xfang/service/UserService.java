package com.xfang.service;

import com.xfang.pojo.User;

public interface UserService {

  User findByUsername(String username);

  void register(String username, String password);

  void update(User user);

  void updateAvatar(String avatarUrl);

  void updatePwd(String newPwd);
}
