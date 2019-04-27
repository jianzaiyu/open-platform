package cn.ce.framework.base.support;


import cn.ce.framework.base.exception.BusinessException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.base.Charsets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

/**
 * @author: ggs
 * @date: 2018-08-14 14:36
 **/
@Slf4j
@Service
public class FileUploadService {
    @Autowired
    private UploadPathProperty uploadPathProperty;
    /**
     * 上传文件
     *
     * @param multipartFile
     * @return 返回图片绝对路径(以后修改成NAS路径)
     * @throws IOException
     */
    public String uploadFile(MultipartFile multipartFile) throws IOException {
        String imagePath = uploadPathProperty.getUploadFolder();
        File file = new File(imagePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
        String fileName = UUID.randomUUID() +
                multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        String fullPath = imagePath + fileName;
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fullPath));
        byte[] bs = new byte[1024];
        int len;
        while ((len = fileInputStream.read(bs)) != -1) {
            bos.write(bs, 0, len);
        }
        bos.flush();
        bos.close();
        log.info("已上传 文件{} 路径={}", fileName, imagePath);
        return uploadPathProperty.getStaticAccessPath() + fileName;
    }

    /**
     * 验证图片
     */
    public FileUploadService validateIMG(MultipartFile multipartFile) {
        String contentType = multipartFile.getContentType();
        if (multipartFile.isEmpty() || !contentType.contains("image")) {
            throw new BusinessException("请上传图片格式文件");
        }
        return this;
    }

    /**
     * 验证SQL文件
     */
    public FileUploadService validateSQL(MultipartFile multipartFile) {
        if (multipartFile.isEmpty() || !multipartFile.getOriginalFilename().endsWith(".sql")) {
            throw new BusinessException("请上传SQL脚本文件");
        }
        return this;
    }

    public JSONArray parseArray(String path) throws IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("json/" + path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charsets.UTF_8));
        StringBuilder stringBuilder = new StringBuilder();
        String temp;
        while ((temp = reader.readLine()) != null) {
            stringBuilder.append(temp);
        }
        return JSON.parseArray(stringBuilder.toString());
    }

}
