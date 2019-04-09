package com.commerce.huayi.service.impl;

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
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImpl implements ImageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageServiceImpl.class);

    @Override
    public ImageUploadVo upload(MultipartFile file) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = "Oun6KWwbXzDsALRPNdj8bDFeAXg-LRZqyR81__W1";
        String secretKey = "E8QUTljGjAAho_V5CQEuDJkPTkn-QwdI8woiL4nT";
        //存储空间的名字
        String bucket = "doubleyangstore";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        ImageUploadVo imageUploadVo = null;
        try {
            Response response = uploadManager.put(file.getBytes(), key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            imageUploadVo = new ImageUploadVo();
            imageUploadVo.setImageKey(putRet.key);
            imageUploadVo.setImageUploadTime(System.currentTimeMillis());
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return imageUploadVo;
    }


    @Override
    public ImageUploadVo upload(byte[] bytes) throws Exception {
        Configuration cfg = new Configuration(Zone.zone2());
        UploadManager uploadManager = new UploadManager(cfg);
        String accessKey = "Oun6KWwbXzDsALRPNdj8bDFeAXg-LRZqyR81__W1";
        String secretKey = "E8QUTljGjAAho_V5CQEuDJkPTkn-QwdI8woiL4nT";
        String bucket = "doubleyangstore";
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
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            LOGGER.error("上传图片至七牛云出错==={}", ExceptionUtils.getStackTrace(ex));
        }

        return imageUploadVo;
    }
}