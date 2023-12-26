package com.xfang.controller;

import com.xfang.pojo.Article;
import com.xfang.pojo.PageBean;
import com.xfang.pojo.Result;
import com.xfang.service.ArticleService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {

  private final ArticleService articleService;

  public ArticleController(ArticleService articleService) {
    this.articleService = articleService;
  }

  @PostMapping
  public Result<String> add(@RequestBody @Validated Article article) {
    articleService.add(article);
    return Result.success();
  }

  public Result<PageBean<Article>> list(
      Integer pageNum,
      Integer pageSize,
      @RequestParam(required = false) Integer categoryId,
      @RequestParam(required = false) String state) {
    PageBean<Article> pageBean = articleService.list(pageNum, pageSize, categoryId, state);
    return Result.success(pageBean);
  }
}
