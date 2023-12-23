import { request } from "@/utils/request.js";

export function userRegisterService(registerForm) {
  const params = new URLSearchParams();
  for (let key in registerForm) {
    params.append(key, registerForm[key]);
  }
  return request({
    url: "/user/register",
    method: "post",
    params,
  });
}
