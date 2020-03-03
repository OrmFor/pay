package com.kinlie.microservicepay.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConstants;
import com.kinlie.microservicepay.common.SystemEnvironmentConfig;
import com.kinlie.microservicepay.controller.base.BaseController;
import com.kinlie.microservicepay.core.ResultCode;
import com.kinlie.microservicepay.core.StatusCode;
import com.kinlie.microservicepay.request.WePayAddReceiverRequest;
import com.kinlie.microservicepay.request.base.WePayBaseConfig;
import com.kinlie.microservicepay.util.wxpay.WxConfigBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


/**
* @Author wwy
* @Description 添加分佣接收者
* @Date 16:05 2019/12/31
* @Param
* @return
**/
@RestController
@RequestMapping("/payapi/1.0")
public class WePayAddReceiverController extends BaseController {

    @Autowired
    private SystemEnvironmentConfig systemEnvironmentConfig;


    @RequestMapping("/wePayAddReceiver")
    public ResultCode wePayAddReceiver(WePayAddReceiverRequest wePayAddReceiverRequest){

        Map<String,String> data = new HashMap<String, String>();
        JSONObject json = new JSONObject();
        json.put("type", wePayAddReceiverRequest.getType());
        json.put("account", wePayAddReceiverRequest.getAccount());

        if(StringUtils.isNotBlank(wePayAddReceiverRequest.getName())){
            data.put("name", wePayAddReceiverRequest.getName());
        }

        json.put("relation_type", wePayAddReceiverRequest.getRelationType());
        if(StringUtils.isNotBlank(wePayAddReceiverRequest.getCustomRelation())){
            data.put("custom_relation", wePayAddReceiverRequest.getCustomRelation());
        }
        data.put("receiver", json.toJSONString());

        WePayBaseConfig wePayBaseConfig = WePayBaseConfig.builder().appId(wePayAddReceiverRequest.getAppId())
                .mchId(wePayAddReceiverRequest.getMchId())
                .key(wePayAddReceiverRequest.getKey())
                .certByte(wePayAddReceiverRequest.getCertByte()).build();
        WxConfigBean wxConfigBean = new WxConfigBean(wePayBaseConfig);
        String result = "";
        try {
            WXPay wxPay = new WXPay(wxConfigBean,systemEnvironmentConfig.isProd());
            Map<String,String> dataSign = wxPay.fillRequestData(data);
            result = wxPay.requestWithoutCert(WXPayConstants.PROFITSHARINGADDRECEIVER_URL_SUFFIX,dataSign,1000,1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultCode(StatusCode.SUCCESS,result);

    }


}
