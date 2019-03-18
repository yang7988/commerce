package com.commerce.huayi.entity.response;

/**
 * Created by admin on 2017/11/29.
 */
public class IpAddress {
    private Integer errNum;
    private String errMsg;
    private IpAddressDetails retData;

    public Integer getErrNum() {
        return errNum;
    }

    public void setErrNum(Integer errNum) {
        this.errNum = errNum;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public IpAddressDetails getRetData() {
        return retData;
    }

    public void setRetData(IpAddressDetails retData) {
        this.retData = retData;
    }
}