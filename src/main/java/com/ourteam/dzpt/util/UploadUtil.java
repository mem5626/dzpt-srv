package com.ourteam.dzpt.util;

import com.ourteam.dzpt.entity.ExceptionMsg;
import com.ourteam.dzpt.exception.GlobalException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UploadUtil {

  @Value("${web.upload-path}")
  private String uploadFilePath;

  public String uploadImage(MultipartFile pic, String imageDir)
      throws Exception {

    String originalFileName = pic.getOriginalFilename();
    String imageName = UUID.randomUUID().toString() + originalFileName
        .substring(originalFileName.lastIndexOf("."));
    String datePath = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
    String fileDirPath = uploadFilePath + datePath + "\\" + imageDir;
    File fileDir = new File(fileDirPath);
    if (!fileDir.exists()) {
      fileDir.mkdirs();
    }
    String filePath = fileDir.getAbsolutePath() + "\\" + imageName;

    try {
      File newFile = new File(filePath);
      pic.transferTo(newFile);
      return imageDir + "\\" + imageName;
    } catch (IOException e) {
      throw new GlobalException(ExceptionMsg.UploadFiled);
    }
  }
}
