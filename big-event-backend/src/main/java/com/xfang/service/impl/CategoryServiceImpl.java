package com.xfang.service.impl;

import com.xfang.mapper.CategoryMapper;
import com.xfang.pojo.Category;
import com.xfang.service.CategoryService;
import com.xfang.utils.ThreadLocalUtil;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

  private final CategoryMapper categoryMapper;

  public CategoryServiceImpl(CategoryMapper categoryMapper) {
    this.categoryMapper = categoryMapper;
  }

  @Override
  public void add(Category category) {
    category.setCreateTime(LocalDateTime.now());
    category.setUpdateTime(LocalDateTime.now());
    Map<String, Object> map = ThreadLocalUtil.get();
    Integer id = (Integer) map.get("id");
    category.setCreateUser(id);
    categoryMapper.add(category);
  }

  @Override
  public List<Category> list() {
    Map<String, Object> map = ThreadLocalUtil.get();
    Integer id = (Integer) map.get("id");
    return categoryMapper.list(id);
  }

  @Override
  public Category findById(Integer id) {
    return categoryMapper.findById(id);
  }

  @Override
  public void update(Category category) {
    category.setUpdateTime(LocalDateTime.now());
    categoryMapper.update(category);
  }

  @Override
  public void delete(Integer id) {
    categoryMapper.delete(id);
  }
}
