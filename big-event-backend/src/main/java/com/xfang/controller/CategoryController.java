package com.xfang.controller;

import com.xfang.pojo.Category;
import com.xfang.pojo.Result;
import com.xfang.service.CategoryService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {

  private final CategoryService categoryService;

  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @PostMapping
  public Result<String> add(@RequestBody @Validated Category category) {
    categoryService.add(category);
    return Result.success();
  }
}
