package com.xfang.mapper;

import com.xfang.pojo.Article;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper {
  @Insert(
      "insert into article (title, content, cover_img, state, category_id, create_time, update_time, create_user) "
          + "values (#{title}, #{content}, #{coverImg}, #{state}, #{categoryId}, #{createTime}, #{updateTime}, #{createUser})")
  void add(Article article);

  List<Article> list(Integer id, Integer categoryId, String state);
}
