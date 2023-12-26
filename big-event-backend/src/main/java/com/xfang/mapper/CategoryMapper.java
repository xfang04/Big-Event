package com.xfang.mapper;

import com.xfang.pojo.Category;
import java.util.List;

import org.apache.ibatis.annotations.*;

@Mapper
public interface CategoryMapper {
  @Insert(
      "insert into category(category_name, category_alias, create_user, create_time, update_time)"
          + "values(#{categoryName}, #{categoryAlias}, #{createUser}, #{createTime}, #{updateTime})")
  void add(Category category);

  @Select("select * from category where create_user = #{id}")
  List<Category> list(Integer id);

  @Select("select * from category where id = #{id}")
  Category findById(Integer id);

  @Update(
      "update category set category_name = #{categoryName}, category_alias = #{categoryAlias}, update_time = #{updateTime} where id = #{id}")
  void update(Category category);

  @Delete("delete from category where id = #{id}")
  void delete(Integer id);
}
