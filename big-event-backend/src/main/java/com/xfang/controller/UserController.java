package com.xfang.controller;

import com.xfang.pojo.Result;
import com.xfang.pojo.User;
import com.xfang.service.UserService;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

  @Autowired private UserService userService;

  @RequestMapping("/register")
  public Result register(
      @Pattern(regexp = "^[a-zA-Z0-9_]{5,16}$", message = "用户名长度不符合要求") String username,
      @Pattern(regexp = "^[a-zA-Z0-9_]{5,16}$", message = "密码长度不符合要求") String password) {

    // search user by username
    User user = userService.findByUsername(username);
    if (user != null) {
      return Result.error("用户名已存在");
    } else {
      // register
      userService.register(username, password);
      return Result.success();
    }
  }
}
