package com.solsist.server.controller;

import com.solsist.server.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@RestController
public class UploadController {
    @Value("${upload.path}") // 配置文件中定义的上传路径
    private String uploadPath;
    @Value("${upload.staticAccessPath}")
    private String staticAccessPath;
    @Value("${upload.accessPath}")
    private String accessPath;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDTO uploadImage(@RequestParam("file") MultipartFile imageFile) {
        try {
            String fileName = imageFile.getOriginalFilename();
            String extName = fileName.substring(fileName.lastIndexOf("."));
            Date date=new Date();
            String temp=date.toString().replaceAll("[^a-z^A-Z^0-9]", "");
            String filePath = uploadPath+temp+extName;
            imageFile.transferTo(new File(filePath));
            String fileAcessDir=accessPath+temp+extName;
            return ResponseDTO.ok(fileAcessDir);
        } catch (IOException e) {
            return ResponseDTO.fail(500);
        }
    }
}