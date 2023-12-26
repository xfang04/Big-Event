//导入请求工具类
import { request } from "@/utils/request.js";

//文章分类列表查询
export const articleCategoryListService = () => {
  return request({
    url: "/category",
    method: "get",
  });
};

export const articleCategoryAddService = (categoryModel) => {
  return request({
    url: "/category",
    method: "post",
    data: categoryModel,
  });
};

//修改分类
export const articleCategoryUpdateService = (categoryModel) => {
  return request({
    url: "/category",
    method: "put",
    data: categoryModel,
  });
};

export const articleCategoryDeleteService = (id) => {
  return request({
    url: "/category",
    method: "delete",
    params: { id },
  });
};
