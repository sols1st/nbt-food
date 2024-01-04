package com.solsist.server.controller;

import com.solsist.server.dto.ResponseDTO;
import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;
import javassist.tools.rmi.StubGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.io.*;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import static com.solsist.server.dto.ResponseDTO.ok;

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
            Date date = new Date();
            String temp = date.toString().replaceAll("[^a-z^A-Z^0-9]", "");
            String filePath = uploadPath + temp + extName;
            imageFile.transferTo(new File(filePath));
            String fileAcessDir = accessPath + temp + extName;
            return ResponseDTO.ok(fileAcessDir);
        } catch (IOException e) {
            return ResponseDTO.fail(500);
        }
    }

    @PostMapping("/base64Upload")
    public ResponseDTO uploadImage(@RequestBody Map<String, String> map) {
        try {
            String base64Data = map.get("image");
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] b = decoder.decode(base64Data);
            for (int i = 0; i < b.length; i++) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            String filename = UUID.randomUUID().toString().concat(".jpg");
            OutputStream out = new FileOutputStream(uploadPath.concat(filename));
            out.write(b);
            out.flush();
            out.close();
            String aPath = accessPath.concat(filename);
            return new ResponseDTO<>(200, aPath, "上传成功");
        } catch (IOException e) {
            return ResponseDTO.fail("上传失败");
        }
    }
}