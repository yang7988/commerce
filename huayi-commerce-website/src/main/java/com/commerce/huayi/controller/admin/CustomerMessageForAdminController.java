package com.commerce.huayi.controller.admin;

import com.commerce.huayi.api.ApiResponse;
import com.commerce.huayi.entity.request.PageRequest;
import com.commerce.huayi.entity.response.CustomerMessageVo;
import com.commerce.huayi.pagination.Page;
import com.commerce.huayi.service.CustomerMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/admin/customerMessage")
@Api(tags = "客户留言管理")
public class CustomerMessageForAdminController {

    @Autowired
    private CustomerMessageService customerMessageService;

    @PostMapping(value = "/getCustomerMessages")
    @ApiOperation(value = "客户留言管理", notes = "获取客户留言")
    public ApiResponse<Page<CustomerMessageVo>> getCustomerMessages(@RequestBody PageRequest pageRequest) {
        return ApiResponse.returnSuccess(customerMessageService.getCustomerMessages(pageRequest));
    }

}
