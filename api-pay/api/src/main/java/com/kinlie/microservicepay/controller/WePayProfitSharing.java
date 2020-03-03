package com.kinlie.microservicepay.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.wxpay.sdk.WXPayConstants;
import com.kinlie.microservicepay.common.SystemEnvironmentConfig;
import com.kinlie.microservicepay.controller.base.BaseController;
import com.kinlie.microservicepay.core.ResultCode;
import com.kinlie.microservicepay.core.StatusCode;
import com.kinlie.microservicepay.request.WePayProfitSharingRequest;
import com.kinlie.microservicepay.request.base.WePayBaseConfig;
import com.kinlie.microservicepay.util.wxpay.WxConfigBean;
import com.kinlie.microservicepay.util.wxpay.WxPayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/payapi/1.0")
public class WePayProfitSharing extends BaseController {

    @Autowired
    private SystemEnvironmentConfig systemEnvironmentConfig;

    @RequestMapping("/profitSharing")
    public ResultCode profitSharing(WePayProfitSharingRequest wePayProfitSharingRequest){
        Map<String,String> data = new HashMap<String, String>();
        data.put("transaction_id", wePayProfitSharingRequest.getTransactionId());
        data.put("out_order_no", wePayProfitSharingRequest.getOutOrderNo());
        data.put("receivers", JSONObject.toJSONString(wePayProfitSharingRequest.getReceivers()));

        WePayBaseConfig wePayBaseConfig = WePayBaseConfig.builder().appId(wePayProfitSharingRequest.getAppId())
                .mchId(wePayProfitSharingRequest.getMchId())
                .key(wePayProfitSharingRequest.getKey())
                .certByte(wePayProfitSharingRequest.getCertByte()).build();
        WxConfigBean wxConfigBean = new WxConfigBean(wePayBaseConfig);
        String result = "";
        try {
            WxPayUtils wxPay = new WxPayUtils(wxConfigBean,systemEnvironmentConfig.isProd());
            Map<String,String> dataSign = wxPay.fillRequestData(data);
            result = wxPay.requestWithCert(WXPayConstants.PROFITSHARING_URL_SUFFIX,dataSign,1000,1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultCode(StatusCode.SUCCESS,result);
    }

}
