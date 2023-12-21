package com.xfang.service.impl;

import com.xfang.mapper.UserMapper;
import com.xfang.pojo.User;
import com.xfang.service.UserService;
import com.xfang.utils.Md5Util;
import com.xfang.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

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

  @Override
  public void update(User user) {
    user.setUpdateTime(LocalDateTime.now());
    userMapper.update(user);
  }

  @Override
  public void updateAvatar(String avatarUrl) {
    Map<String, Object> map = ThreadLocalUtil.get();
    Integer id = (Integer) map.get("id");
    userMapper.updateAvatar(avatarUrl, id);
  }

  @Override
  public void updatePwd(String newPwd) {
    Map<String, Object> map = ThreadLocalUtil.get();
    Integer id = (Integer) map.get("id");
    String md5Password = Md5Util.getMD5String(newPwd);
    userMapper.updatePwd(md5Password, id);
  }
}
