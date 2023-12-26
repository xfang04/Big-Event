package com.xfang.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Category {
  private Integer id; // 主键ID
  @NotEmpty private String categoryName; // 分类名称
  @NotEmpty private String categoryAlias; // 分类别名
  private Integer createUser; // 创建人ID

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime createTime; // 创建时间

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime updateTime; // 更新时间
}
