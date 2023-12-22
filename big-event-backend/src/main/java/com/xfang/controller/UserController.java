package com.xfang.controller;

import com.xfang.pojo.Result;
import com.xfang.pojo.User;
import com.xfang.service.UserService;
import com.xfang.utils.JwtUtil;
import com.xfang.utils.Md5Util;
import com.xfang.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import java.util.HashMap;
import java.util.Map;
import org.hibernate.validator.constraints.URL;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public Result<String> register(
      @Pattern(regexp = "^[a-zA-Z0-9_]{5,16}$", message = "用户名长度不符合要求") String username,
      @Pattern(regexp = "^[a-zA-Z0-9_]{5,16}$", message = "密码长度不符合要求") String password) {

    // search user by username
    User user = userService.findByUsername(username);
    if (user != null) {
      return Result.error("用户名已存在");
    }
    userService.register(username, password);
    return Result.success();
  }

  @PostMapping("/login")
  public Result<String> login(
      @Pattern(regexp = "^[a-zA-Z0-9_]{5,16}$", message = "用户名长度不符合要求") String username,
      @Pattern(regexp = "^[a-zA-Z0-9_]{5,16}$", message = "密码长度不符合要求") String password) {

    // search user by username
    User user = userService.findByUsername(username);
    if (user == null) {
      return Result.error("用户名不存在");
    }

    // login
    if (Md5Util.getMD5String(password).equals(user.getPassword())) {
      Map<String, Object> claims = new HashMap<>();
      claims.put("id", user.getId());
      claims.put("username", username);
      String token = JwtUtil.genToken(claims);
      return Result.success(token);
    } else {
      return Result.error("密码错误");
    }
  }

  @GetMapping("/userInfo")
  public Result<User> userInfo() {
    Map<String, Object> claims = ThreadLocalUtil.get();
    String username = (String) claims.get("username");
    User user = userService.findByUsername(username);
    return Result.success(user);
  }

  @PutMapping("/update")
  public Result<String> update(@RequestBody @Validated User user) {
    Map<String, Object> claims = ThreadLocalUtil.get();
    Integer id = (Integer) claims.get("id");
    if (!id.equals(user.getId())) {
      return Result.error("非法操作");
    }
    userService.update(user);
    return Result.success();
  }

  @PatchMapping("/updateAvatar")
  public Result<String> updateAvatar(@RequestParam @URL String avatarUrl) {
    userService.updateAvatar(avatarUrl);
    return Result.success();
  }

  @PatchMapping("/updatePwd")
  public Result<String> updatePwd(@RequestBody Map<String, String> params) {
    String oldPwd = params.get("old_pwd");
    String newPwd = params.get("new_pwd");
    String rePwd = params.get("re_pwd");
    if (!StringUtils.hasLength(oldPwd)
        || !StringUtils.hasLength(newPwd)
        || !StringUtils.hasLength(rePwd)) {
      return Result.error("缺少参数");
    }

    if (!newPwd.equals(rePwd)) {
      return Result.error("两次密码不一致");
    }

    if (newPwd.equals(oldPwd)) {
      return Result.error("新密码不能与旧密码相同");
    }

    java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("^[a-zA-Z0-9_]{5,16}$");
    if (!pattern.matcher(newPwd).matches()) {
      return Result.error("密码长度不符合要求");
    }

    Map<String, Object> map = ThreadLocalUtil.get();
    String username = (String) map.get("username");
    User user = userService.findByUsername(username);
    if (!Md5Util.getMD5String(oldPwd).equals(user.getPassword())) {
      return Result.error("原密码错误");
    }
    userService.updatePwd(newPwd);
    return Result.success();
  }
}
