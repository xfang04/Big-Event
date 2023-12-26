package com.xfang.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xfang.mapper.ArticleMapper;
import com.xfang.pojo.Article;
import com.xfang.pojo.PageBean;
import com.xfang.service.ArticleService;
import com.xfang.utils.ThreadLocalUtil;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {
  private final ArticleMapper articleMapper;

  public ArticleServiceImpl(ArticleMapper articleMapper) {
    this.articleMapper = articleMapper;
  }

  @Override
  public void add(Article article) {
    article.setCreateTime(LocalDateTime.now());
    article.setUpdateTime(LocalDateTime.now());
    Map<String, Object> map = ThreadLocalUtil.get();
    Integer id = (Integer) map.get("id");
    article.setCreateUser(id);
    articleMapper.add(article);
  }

  @Override
  public PageBean<Article> list(
      Integer pageNum, Integer pageSize, Integer categoryId, String state) {
    PageBean<Article> pageBean = new PageBean<>();
    PageHelper.startPage(pageNum, pageSize);
    Map<String, Object> map = ThreadLocalUtil.get();
    Integer id = (Integer) map.get("id");
    List<Article> articles = articleMapper.list(id, categoryId, state);
    Page<Article> page = (Page<Article>) articles;
    pageBean.setTotal(page.getTotal());
    pageBean.setItems(page.getResult());
    return pageBean;
  }
}
