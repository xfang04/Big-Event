package com.xfang.controller;

import com.xfang.pojo.Result;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {

  @RequestMapping("/upload")
  public Result<String> upload(MultipartFile file) throws IOException {
    String originalFilename = file.getOriginalFilename();
    if (originalFilename == null) {
      return Result.error("文件名为空");
    }
    String fileName =
        UUID.randomUUID().toString()
            + originalFilename.substring(originalFilename.lastIndexOf("."));
    file.transferTo(new File("/Users/xfang/Downloads/" + fileName));
    return Result.success();
  }
}
