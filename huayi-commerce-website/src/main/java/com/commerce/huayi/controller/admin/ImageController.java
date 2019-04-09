package com.commerce.huayi.controller.admin;

import com.commerce.huayi.api.ApiResponse;
import com.commerce.huayi.entity.response.ImageUploadVo;
import com.commerce.huayi.service.ImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value ="/api/image")
@Api(tags = "图片上传API")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping(value = "/upload")
    @ApiOperation(value = "上传图片",notes = "上传图片")
    public ApiResponse<ImageUploadVo> upload(@RequestParam(value = "file") MultipartFile file) throws Exception {
        return ApiResponse.returnSuccess(imageService.upload(file));
    }
}