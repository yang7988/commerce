package com.commerce.huayi.controller.website;

import com.commerce.huayi.api.ApiResponseEnum;
import com.commerce.huayi.entity.response.ApiStatusCodeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/status")
@Api(tags = "自定义API状态码查询")
public class ApiDescriptionController {

    @PostMapping(value = "/code")
    @ApiOperation(value = "返回系统状态码",notes = "返回状态码及描述")
    public List<ApiStatusCodeVo> code() {
        ApiResponseEnum[] values = ApiResponseEnum.values();
        return Stream.of(values).map(apiEnum -> new ApiStatusCodeVo(apiEnum.getId(), apiEnum.getCode(), apiEnum.getLabel()))
                .collect(Collectors.toList());
    }
}