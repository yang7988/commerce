package com.commerce.huayi.service;

import com.commerce.huayi.entity.response.ImageUploadVo;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    ImageUploadVo upload(MultipartFile file) throws Exception;

    ImageUploadVo upload(byte[] bytes) throws Exception;
}
