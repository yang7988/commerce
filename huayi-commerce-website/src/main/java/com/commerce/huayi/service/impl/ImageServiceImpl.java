package com.commerce.huayi.service.impl;

import com.commerce.huayi.configuration.QiniuConfiguration;
import com.commerce.huayi.entity.response.ImageUploadVo;
import com.commerce.huayi.service.ImageService;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImpl implements ImageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageServiceImpl.class);

    @Autowired
    private QiniuConfiguration qiniuConfiguration;

    @Override
    public ImageUploadVo upload(MultipartFile file) throws Exception {
        return uploadBinaryFile(file.getBytes());
    }

    @Override
    public ImageUploadVo upload(byte[] bytes) throws Exception {
        return uploadBinaryFile(bytes);
    }

    private ImageUploadVo uploadBinaryFile(byte[] bytes) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = qiniuConfiguration.getAccessKey();
        String secretKey = qiniuConfiguration.getSecretKey();
        //存储空间的名字
        String bucket = qiniuConfiguration.getBucket();
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        ImageUploadVo imageUploadVo = null;
        try {
            Response response = uploadManager.put(bytes, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            imageUploadVo = new ImageUploadVo();
            imageUploadVo.setImageKey(putRet.key);
            imageUploadVo.setImageUploadTime(System.currentTimeMillis());
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            LOGGER.error("图片上传失败==={}",r.toString());
            try {
                System.err.println(r.bodyString());
                LOGGER.error("图片上传出错==={}",r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
        return imageUploadVo;
    }
}