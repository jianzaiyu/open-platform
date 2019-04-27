package cn.ce.services.account.controller;

import cn.ce.framework.base.support.FileUploadService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;

@RestController
public class UploadController {
    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping("picture")
    public JSONObject picUpload(@NotNull(message = "上传的图片不允许为NULL") MultipartFile file) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("imagePath", fileUploadService.validateIMG(file).uploadFile(file));
        return jsonObject;
    }

    @PostMapping("sqlShell")
    public JSONObject sqlUpload(@NotNull(message = "上传的SQL脚本不允许为NULL") MultipartFile file) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("imagePath", fileUploadService.validateSQL(file).uploadFile(file));
        return jsonObject;
    }

}
