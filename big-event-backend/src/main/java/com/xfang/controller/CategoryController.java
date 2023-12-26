package com.xfang.controller;

import com.xfang.pojo.Category;
import com.xfang.pojo.Result;
import com.xfang.service.CategoryService;
import com.xfang.utils.ThreadLocalUtil;
import java.util.List;
import java.util.Map;
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
  public Result<String> add(@RequestBody @Validated(Category.Add.class) Category category) {
    categoryService.add(category);
    return Result.success();
  }

  @GetMapping()
  public Result<List<Category>> list() {
    List<Category> categories = categoryService.list();
    return Result.success(categories);
  }

  @GetMapping("/detail")
  public Result<Category> detail(Integer id) {
    Category category = categoryService.findById(id);
    return Result.success(category);
  }

  @PutMapping
  public Result<String> update(@RequestBody @Validated(Category.Update.class) Category category) {
    categoryService.update(category);
    return Result.success();
  }

  @DeleteMapping
  public Result<String> delete(Integer id) {
    Map<String, Object> map = ThreadLocalUtil.get();
    Integer userId = (Integer) map.get("id");
    Category category = categoryService.findById(id);
    if (!category.getCreateUser().equals(userId)) {
      return Result.error("无权限");
    }
    categoryService.delete(id);
    return Result.success();
  }
}
