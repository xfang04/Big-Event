import { request } from "@/utils/request.js";

export const userRegisterService = (registerForm) => {
  const params = new URLSearchParams();
  for (let key in registerForm) {
    params.append(key, registerForm[key]);
  }
  return request({
    url: "/user/register",
    method: "post",
    params,
  });
};

export const userLoginService = (loginForm) => {
  const params = new URLSearchParams();
  for (let key in loginForm) {
    params.append(key, loginForm[key]);
  }
  return request({
    url: "/user/login",
    method: "post",
    params,
  });
};
