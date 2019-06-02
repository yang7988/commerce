package com.commerce.huayi.service.impl;

import com.commerce.huayi.api.ApiResponseEnum;
import com.commerce.huayi.api.BusinessException;
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
        return uploadBinaryFile(file.getBytes(), Zone.zone0(),0);
    }

    @Override
    public ImageUploadVo upload(byte[] bytes) throws Exception {
        return uploadBinaryFile(bytes, Zone.zone0(),0);
    }

    private ImageUploadVo uploadBinaryFile(byte[] bytes, Zone zone,int count) {
        try {
            if(count > 2) {
                throw new BusinessException(ApiResponseEnum.UPLOAD_IMAGE_MAX_TRIES);
            }
            //构造一个带指定Zone对象的配置类
            Configuration cfg = new Configuration(zone);
            //...其他参数参考类注释
            UploadManager uploadManager = new UploadManager(cfg);
            //...生成上传凭证，然后准备上传
            String accessKey = qiniuConfiguration.getAccessKey();
            String secretKey = qiniuConfiguration.getSecretKey();
            //存储空间的名字
            String bucket = qiniuConfiguration.getBucket().toLowerCase();
            //默认不指定key的情况下，以文件内容的hash值作为文件名
            String key = null;
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            Response response = uploadManager.put(bytes, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            ImageUploadVo imageUploadVo = new ImageUploadVo();
            imageUploadVo.setImageKey(putRet.key);
            imageUploadVo.setImageUploadTime(System.currentTimeMillis());
            return imageUploadVo;
        } catch (QiniuException ex) {
            Response r = ex.response;
            String errorMsg = r.toString();
            LOGGER.error("图片上传失败==={}", errorMsg);
            if(r.statusCode == 400 && errorMsg.contains("incorrect region")) {
                Zone region = null;
                if (errorMsg.contains("please use up-z0")) {
                    region = Zone.zone0();
                } else if (errorMsg.contains("please use up-z1")) {
                    region = Zone.zone1();
                } else if (errorMsg.contains("please use up-z2")) {
                    region = Zone.zone2();
                }
                if(region != null) {
                    return uploadBinaryFile(bytes,region,count++);
                }
            }
            return null;
        } catch (Exception e) {
            throw new BusinessException(ApiResponseEnum.UPLOAD_IMAGE_ERROR);
        }
    }
}