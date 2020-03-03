package com.kinlie.microservicepay.controller;

import com.github.wxpay.sdk.WXPay;
import com.kinlie.microservicepay.common.SystemEnvironmentConfig;
import com.kinlie.microservicepay.controller.base.BaseController;
import com.kinlie.microservicepay.core.ResultCode;
import com.kinlie.microservicepay.core.StatusCode;
import com.kinlie.microservicepay.request.WePayUnifiedOrderRequest;
import com.kinlie.microservicepay.request.base.WePayBaseConfig;
import com.kinlie.microservicepay.util.wxpay.WxConfigBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/payapi/1.0")
public class WePayUnifiedorderController extends BaseController {


    @Autowired
    private SystemEnvironmentConfig systemEnvironmentConfig;

    @RequestMapping("/unifiedOrder")
    public ResultCode unifiedOrder(@Valid WePayUnifiedOrderRequest unifiedOrderRequest){

        String notifyUrl =  this.getDomain()+"/payapi/1.0/notify";
        Map<String,String> data = new HashMap<String, String>();
        data.put("body", unifiedOrderRequest.getBody());//商品描述
        data.put("out_trade_no", unifiedOrderRequest.getOutTradeNo());
        data.put("total_fee", unifiedOrderRequest.getTotalFee());
        data.put("spbill_create_ip", unifiedOrderRequest.getSpbillCreateIp());
        data.put("notify_url", notifyUrl);
        data.put("trade_type", unifiedOrderRequest.getTradeType());
        data.put("openid",unifiedOrderRequest.getOpenid());
        data.put("profit_sharing",unifiedOrderRequest.getProfitSharing());

        if(StringUtils.isNotBlank(unifiedOrderRequest.getDetail())){
            data.put("detail", unifiedOrderRequest.getDetail());

        }
        if(StringUtils.isNotBlank(unifiedOrderRequest.getDeviceInfo())){
            data.put("device_info", unifiedOrderRequest.getDeviceInfo());
        }

        WePayBaseConfig bean = WePayBaseConfig.builder().appId(unifiedOrderRequest.getAppId())
                .mchId(unifiedOrderRequest.getMchId())
                .key(unifiedOrderRequest.getKey())
                .certByte(unifiedOrderRequest.getCertByte()).build();
        WxConfigBean wxConfigBean = new WxConfigBean(bean);
        Map<String, String> result = new HashMap<>();
        try {
            WXPay wxPay = new WXPay(wxConfigBean,systemEnvironmentConfig.isProd());
            result = wxPay.unifiedOrder(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultCode(StatusCode.SUCCESS,result);

    }


}
