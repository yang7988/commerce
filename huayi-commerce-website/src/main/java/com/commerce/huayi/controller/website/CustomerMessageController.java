package com.commerce.huayi.controller.website;

import com.commerce.huayi.api.ApiResponse;
import com.commerce.huayi.entity.request.CustomerMessageReq;
import com.commerce.huayi.service.CustomerMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/customerMessage")
@Api(value = "客户留言管理")
public class CustomerMessageController {

    @Autowired
    private CustomerMessageService customerMessageService;

    @PostMapping(value = "/getCustomerMessages")
    @ApiOperation(value = "客户留言管理",notes = "获取客户留言")
    public ApiResponse getCustomerMessages() {
        return ApiResponse.returnSuccess(customerMessageService.getCustomerMessages());
    }

    @PostMapping(value = "/addCustomerMessage")
    @ApiOperation(value = "客户留言管理",notes = "添加客户留言")
    public ApiResponse addCustomerMessage(@Valid @RequestBody CustomerMessageReq customerMessageReq, BindingResult bindingResult) {
        customerMessageService.addCustomerMessage(customerMessageReq);
        return ApiResponse.returnSuccess();
    }

}
