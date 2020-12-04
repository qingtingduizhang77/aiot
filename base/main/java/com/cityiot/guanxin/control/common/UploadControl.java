package com.cityiot.guanxin.control.common;

import com.cityiot.guanxin.service.common.UploadService;

import io.swagger.annotations.Api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import swallow.framework.web.ApiResult;

import java.io.IOException;

@Api(tags = "文件上存API接口")
@RestController
@RequestMapping("/api/upload")
public class UploadControl {
	private static final Logger log = LoggerFactory.getLogger(UploadControl.class);
    @Autowired
    private UploadService uploadService;

    @PostMapping("/uploadFile/{type}")
    //@RequiresRoles(value = {"super_admin,admin"},logical = Logical.OR)
    public ApiResult upload(@PathVariable("type") int type,@RequestParam("file") MultipartFile file) {
        String finalFileUrl = "";
        try {
            finalFileUrl = uploadService.upload(type,file);
           return ApiResult.success(finalFileUrl);
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ApiResult.fail("上传失败");
        }
    }
}
