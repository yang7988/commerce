package com.commerce.huayi.service.impl;

import com.commerce.huayi.entity.db.CustomerMessage;
import com.commerce.huayi.entity.request.CustomerMessageReq;
import com.commerce.huayi.entity.request.PageRequest;
import com.commerce.huayi.entity.response.CustomerMessageVo;
import com.commerce.huayi.entity.response.GoodsSpuDetailsVo;
import com.commerce.huayi.entity.response.GoodsSpuVo;
import com.commerce.huayi.mapper.CustomerMessageMapper;
import com.commerce.huayi.pagination.Page;
import com.commerce.huayi.service.CustomerMessageService;
import com.commerce.huayi.service.GoodsService;
import com.commerce.huayi.utils.BeanCopyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerMessageServiceImpl implements CustomerMessageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerMessageServiceImpl.class);

    @Autowired
    private CustomerMessageMapper customerMessageMapper;

    @Autowired
    private GoodsService goodsService;

    @Override
    public Page<CustomerMessageVo> getCustomerMessages(PageRequest pageRequest) {

        int count = customerMessageMapper.getCustomerMessagesTotalCount();
        Page<CustomerMessageVo> page = Page.create(pageRequest.getPageIndex(), pageRequest.getPageMaxSize(), count);
        if (count <= 0) {
            return page;
        }
        List<CustomerMessage> customerMessageList = customerMessageMapper.getCustomerMessages(page.getOffset(), page.getPageMaxSize());
        for(CustomerMessage customerMessage: customerMessageList) {
            List<GoodsSpuVo> GoodsSpus = new ArrayList<GoodsSpuVo>();
            List<Long> goodsId = customerMessageMapper.getGoodIdsForCustomerMessagesId(customerMessage.getId());
            for(Long gid : goodsId){
                GoodsSpuVo goodsSpuVo = new GoodsSpuVo();
                goodsSpuVo.setId(gid);
                GoodsSpuDetailsVo goodsSpuDetailsVo = goodsService.goodsSpecDetails(gid,0L);
                goodsSpuVo.setGoodsName(goodsSpuDetailsVo.getGoodsName());
                goodsSpuVo.setGoodsImageKey(goodsSpuDetailsVo.getGoodsImageKey());
                GoodsSpus.add(goodsSpuVo);
            }
            customerMessage.setGoodsSpus(GoodsSpus);
        }
        page.setList(BeanCopyUtil.copy(CustomerMessageVo.class, customerMessageList));
        return page;
    }

    @Override
    @Transactional
    public void addCustomerMessage(CustomerMessageReq customerMessageReq) {

        LOGGER.info("CustomerMessageServiceImpl->addCustomerMessage:{}", customerMessageReq);
        CustomerMessage customerMessage = new CustomerMessage();
        String id = UUID.randomUUID().toString();
        customerMessage.setAccessPurpose(customerMessageReq.getAccessPurpose());
        customerMessage.setAddress(customerMessageReq.getAddress());
        customerMessage.setCompany(customerMessageReq.getCompany());
        customerMessage.setDealFlag("0");
        customerMessage.setDocumentQuality(customerMessageReq.getDocumentQuality());
        customerMessage.setFax(customerMessageReq.getFax());
        customerMessage.setId(id);
        customerMessage.setMailAddress(customerMessageReq.getMailAddress());
        customerMessage.setMobilePhone(customerMessageReq.getMobilePhone());
        customerMessage.setName(customerMessageReq.getName());
        customerMessage.setOpinion(customerMessageReq.getOpinion());
        customerMessage.setPostalCode(customerMessageReq.getPostalCode());
        customerMessage.setPurposeFlag(customerMessageReq.getPurposeFlag());
        customerMessageMapper.addCustomerMessage(customerMessage);
        List<String> lists = customerMessageReq.getGoodsIds();
        for(String goodsId : lists) {
            customerMessageMapper.addCustomerMessageForGoods(id, goodsId);
        }

    }

    @Override
    public List<CustomerMessageVo> getExportCustomerMessages() {
        List<CustomerMessage> customerMessageList = customerMessageMapper.getExportCustomerMessages();
        for(CustomerMessage customerMessage: customerMessageList) {
            List<GoodsSpuVo> GoodsSpus = new ArrayList<GoodsSpuVo>();
            List<Long> goodsId = customerMessageMapper.getGoodIdsForCustomerMessagesId(customerMessage.getId());
            for(Long gid : goodsId){
                GoodsSpuVo goodsSpuVo = new GoodsSpuVo();
                goodsSpuVo.setId(gid);
                GoodsSpuDetailsVo goodsSpuDetailsVo = goodsService.goodsSpecDetails(gid,0L);
                goodsSpuVo.setGoodsName(goodsSpuDetailsVo.getGoodsName());
                goodsSpuVo.setGoodsImageKey(goodsSpuDetailsVo.getGoodsImageKey());
                GoodsSpus.add(goodsSpuVo);
            }
            customerMessage.setGoodsSpus(GoodsSpus);
        }
        return BeanCopyUtil.copy(CustomerMessageVo.class, customerMessageList);
    }

}
