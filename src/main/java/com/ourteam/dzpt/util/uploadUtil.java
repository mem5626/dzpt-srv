package com.ourteam.dzpt.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class uploadUtil {

    public static String uploadImage(MultipartFile pic, String imageDir) throws IllegalStateException, IOException {

        String originalFileName = pic.getOriginalFilename();
        String imageName = UUID.randomUUID().toString() + originalFileName.substring(originalFileName.lastIndexOf("."));

        String fileDirPath = "src/main/resources/upload/" + imageDir;
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
            e.printStackTrace();
        }
        return null;
    }
}
