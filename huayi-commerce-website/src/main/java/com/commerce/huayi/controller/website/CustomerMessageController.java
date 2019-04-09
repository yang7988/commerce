package com.commerce.huayi.controller.website;

import com.commerce.huayi.api.ApiResponse;
import com.commerce.huayi.entity.request.CustomerMessageReq;
import com.commerce.huayi.service.CustomerMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/customerMessage")
@Api(tags = "客户留言")
public class CustomerMessageController {

    @Autowired
    private CustomerMessageService customerMessageService;

    @PostMapping(value = "/addCustomerMessage")
    @ApiOperation(value = "客户留言",notes = "添加客户留言")
    public ApiResponse addCustomerMessage(@RequestBody CustomerMessageReq customerMessageReq) {
        customerMessageService.addCustomerMessage(customerMessageReq);
        return ApiResponse.returnSuccess();
    }

}
