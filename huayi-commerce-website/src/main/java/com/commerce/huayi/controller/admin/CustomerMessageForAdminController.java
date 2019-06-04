package com.commerce.huayi.controller.admin;

import com.commerce.huayi.api.ApiResponse;
import com.commerce.huayi.entity.request.DelDataForStringReq;
import com.commerce.huayi.entity.request.PageRequest;
import com.commerce.huayi.entity.response.CustomerMessageVo;
import com.commerce.huayi.entity.response.CustomerMessageVoExport;
import com.commerce.huayi.entity.response.GoodsSpuVo;
import com.commerce.huayi.pagination.Page;
import com.commerce.huayi.service.CustomerMessageService;
import com.commerce.huayi.utils.ExportExcel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

    @PostMapping(value = "/delCustomerMessages")
    @ApiOperation(value = "客户留言管理", notes = "删除客户留言")
    public ApiResponse delCustomerMessage(@RequestBody DelDataForStringReq param) {
        customerMessageService.delCustomerMessage(param.getId());
        return ApiResponse.returnSuccess();
    }

    @PostMapping(value = "/exportCustomerMessages")
    @ApiOperation(value = "客户留言管理", notes = "导出客户留言")
    public ApiResponse exportCustomerMessages() {
        List<CustomerMessageVo> lists = customerMessageService.getExportCustomerMessages();
        List<CustomerMessageVoExport> EXportList = new ArrayList<CustomerMessageVoExport>();

        //excel文件名
        String fileName = "留言信息表"+System.currentTimeMillis()+".xlsx";

        for(int i=0; i<lists.size(); i++) {
            CustomerMessageVoExport customerMessageVoExport = new CustomerMessageVoExport();
            CustomerMessageVo customerMessageVo = lists.get(i);
            customerMessageVoExport.setName(customerMessageVo.getName());
            customerMessageVoExport.setCompany(customerMessageVo.getCompany());
            customerMessageVoExport.setMobilePhone(customerMessageVo.getMobilePhone());
            customerMessageVoExport.setMailAddress(customerMessageVo.getMailAddress());
            customerMessageVoExport.setFax(customerMessageVo.getFax());
            customerMessageVoExport.setPostalCode(customerMessageVo.getPostalCode());
            customerMessageVoExport.setAddress(customerMessageVo.getAddress());
            customerMessageVoExport.setOpinion(customerMessageVo.getOpinion());
            customerMessageVoExport.setAccessPurpose(customerMessageVo.getAccessPurpose());
            if(StringUtils.isNotBlank(customerMessageVo.getPurposeFlag())) {
                if("0".equals(customerMessageVo.getPurposeFlag())) {
                    customerMessageVoExport.setPurposeFlag("否");
                } else if ("1".equals(customerMessageVo.getPurposeFlag())) {
                    customerMessageVoExport.setPurposeFlag("是");
                } else if ("2".equals(customerMessageVo.getPurposeFlag())) {
                    customerMessageVoExport.setPurposeFlag("一部分");
                } else if ("3".equals(customerMessageVo.getPurposeFlag())) {
                    customerMessageVoExport.setPurposeFlag("仅浏览");
                }
            }
            if(StringUtils.isNotBlank(customerMessageVo.getDealFlag())) {
                if("0".equals(customerMessageVo.getDealFlag())) {
                    customerMessageVoExport.setDealFlag("否");
                } else if ("1".equals(customerMessageVo.getDealFlag())) {
                    customerMessageVoExport.setDealFlag("是");
                }
            }
            if(StringUtils.isNotBlank(customerMessageVo.getDocumentQuality())) {
                if("0".equals(customerMessageVo.getDocumentQuality())) {
                    customerMessageVoExport.setDocumentQuality("差");
                } else if ("1".equals(customerMessageVo.getDocumentQuality())) {
                    customerMessageVoExport.setDocumentQuality("平均");
                } else if ("2".equals(customerMessageVo.getDocumentQuality())) {
                    customerMessageVoExport.setDocumentQuality("好");
                } else if ("3".equals(customerMessageVo.getDocumentQuality())) {
                    customerMessageVoExport.setDocumentQuality("非常好");
                }
            }
            customerMessageVoExport.setCreateDate(customerMessageVo.getCreateDate());

            String goodsDesc = "";
            if(null != customerMessageVo.getGoodsSpus() && customerMessageVo.getGoodsSpus().size() > 0) {
                for(GoodsSpuVo goodsSpuVo : customerMessageVo.getGoodsSpus()) {
                    goodsDesc = goodsDesc + ",["+ String.valueOf(goodsSpuVo.getId()) + "," + goodsSpuVo.getGoodsName() + "]";
                }
            }
            if(StringUtils.isNotBlank(goodsDesc)) {
                customerMessageVoExport.setGoodsSpus(goodsDesc.substring(1, goodsDesc.length()));
            }

            EXportList.add(customerMessageVoExport);
        }
        try {
            new ExportExcel("留言信息表", CustomerMessageVoExport.class).setDataList(EXportList).writeFile(fileName).dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ApiResponse.returnSuccess("/download/"+fileName);

    }


}
