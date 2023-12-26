package com.xfang.service;

import com.xfang.pojo.Category;
import java.util.List;

public interface CategoryService {
  void add(Category category);

  List<Category> list();
}
