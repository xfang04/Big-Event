package com.xfang.controller;

import com.xfang.pojo.Category;
import com.xfang.pojo.Result;
import com.xfang.service.CategoryService;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

  @GetMapping()
  public Result<List<Category>> list() {
    List<Category> categories = categoryService.list();
    return Result.success(categories);
  }
}
