//定制请求的实例

//导入axios  npm install axios
import axios from "axios";
import {ElMessage} from "element-plus";
import {useTokenStore} from "@/stores/token.js";
import router from "@/router/index.js";
//定义一个变量,记录公共的前缀  ,  baseURL
const baseURL = "/api";
const request = axios.create({ baseURL });
request.interceptors.request.use(
  (config) => {
    let tokenStore = useTokenStore();
    if (tokenStore.token) {
      config.headers.Authorization = tokenStore.token;
    }
    return config;
  },
  (error) => {
    // 对请求错误做些什么
    return Promise.reject(error);
  },
);
//添加响应拦截器
request.interceptors.response.use(
  (result) => {
    if (result.data.code === 0) {
      return result.data;
    }
    ElMessage.error(result.data.msg ? result.data.msg : "服务异常");
    return Promise.reject(result.data);
  },
  (err) => {
    if (err.response && err.response.status === 401) {
      useTokenStore().removeToken();
      ElMessage.error("登录已过期，请重新登录");
      router.push("/login");
    }
    // alert("服务异常");
    return Promise.reject(err); //异步的状态转化成失败的状态
  },
);

export { request };
