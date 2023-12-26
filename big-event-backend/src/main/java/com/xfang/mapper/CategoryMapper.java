package com.xfang.mapper;

import com.xfang.pojo.Category;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CategoryMapper {
  @Insert(
      "insert into category(category_name, category_alias, create_user, create_time, update_time)"
          + "values(#{categoryName}, #{categoryAlias}, #{createUser}, #{createTime}, #{updateTime})")
  void add(Category category);

  @Select("select * from category where create_user = #{id}")
  List<Category> list(Integer id);
}
