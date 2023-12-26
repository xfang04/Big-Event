package com.xfang.service;

import com.xfang.pojo.Article;
import com.xfang.pojo.PageBean;

public interface ArticleService {
  void add(Article article);

  PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);
}
