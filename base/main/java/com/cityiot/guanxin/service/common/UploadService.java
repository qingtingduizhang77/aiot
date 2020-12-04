package com.cityiot.guanxin.service.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {
	
	private static final Logger log = LoggerFactory.getLogger(UploadService.class);
    private String urlPrefix = "/guanxin/";
    @Value("${file.upload.baseUpLoadUrl}")
    private String baseUpLoadUrl;
    @Autowired
    private List<IUploadFileTypeHandle> uploadFileTypeHandleList;
    public String upload(int type,MultipartFile file) throws IOException {
        String finalFileUrl = "";
        for (IUploadFileTypeHandle uploadFileTypeHandle:uploadFileTypeHandleList) {
            if(uploadFileTypeHandle.support(type)){
                String dirName = uploadFileTypeHandle.getFileStroeDirectory();
                finalFileUrl = store(file,dirName);
            }
        }
        return finalFileUrl;
    }

    public String store(MultipartFile file, String dir) throws IOException {
        // 来源文件名
        String originalFilename = file.getOriginalFilename();
        // 来源文件名后缀
        String suffix =  originalFilename.substring(originalFilename.lastIndexOf(".")+1);
        // 计算后的上传文件名
        String uploadFileName = System.currentTimeMillis()+"."+suffix;
        // 上传文件目录
        File dirFile = Paths.get(baseUpLoadUrl,"assets",urlPrefix,dir).toFile();
        if(!dirFile.exists()){
            dirFile.mkdirs();
        }
        // 上传文件路径
        Path path = Paths.get(baseUpLoadUrl,"assets",urlPrefix,dir,"/",uploadFileName);
        File out = path.toFile();
        file.transferTo(out);
        try {
        	log.error("#########URI#########:"+file.getResource().getURI());
        System.out.println("#########URI#########:"+file.getResource().getURI());
        log.error("#########URLLLLLL#########:"+file.getResource().getURL());
        System.out.println("#########URLLLLLL#########:"+file.getResource().getURL());
        log.error("#########Filename#########:"+file.getResource().getFilename());
        System.out.println("#########Filename#########:"+file.getResource().getFilename());
        log.error("#########Description#########:"+file.getResource().getDescription());
        System.out.println("#########Description#########:"+file.getResource().getDescription());
        }catch (Exception e) {
		   e.printStackTrace();
		   log.error("#########URI#########:"+e.getMessage());
		}
        
        return "/assets"+urlPrefix+dir+uploadFileName;
    }

    public File findUploadFile(String fileUrl) {
        if(StringUtils.isEmpty(fileUrl)){
            return null;
        }
        Path path = Paths.get(baseUpLoadUrl,fileUrl);
        File file = path.toFile();
        return file;
    }
}
