package com.xfang.service.impl;

import com.xfang.mapper.CategoryMapper;
import com.xfang.pojo.Category;
import com.xfang.service.CategoryService;
import com.xfang.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

  @Autowired private CategoryMapper categoryMapper;

  @Override
  public void add(Category category) {
    category.setCreateTime(LocalDateTime.now());
    category.setUpdateTime(LocalDateTime.now());
    Map<String, Object> map = ThreadLocalUtil.get();
    Integer id = (Integer) map.get("id");
    category.setCreateUser(id);
    categoryMapper.add(category);
  }
}
