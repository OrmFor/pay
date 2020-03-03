package com.kinlie.microservicepay.controller;

import com.github.wxpay.sdk.WXPay;
import com.kinlie.microservicepay.common.SystemEnvironmentConfig;
import com.kinlie.microservicepay.controller.base.BaseController;
import com.kinlie.microservicepay.core.ResultCode;
import com.kinlie.microservicepay.core.StatusCode;
import com.kinlie.microservicepay.request.WePayRefundConfigWePay;
import com.kinlie.microservicepay.request.base.WePayBaseConfig;
import com.kinlie.microservicepay.util.wxpay.WxConfigBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/payapi/1.0")
public class WePayRefundController extends BaseController {

    @Autowired
    private SystemEnvironmentConfig systemEnvironmentConfig;

    @RequestMapping("/wepayRefund")
    public ResultCode wepayRefund(WePayRefundConfigWePay wePayRefundRequest){
        Map<String,String> data = new HashMap<String, String>();
        data.put("transaction_id", wePayRefundRequest.getTransactionId());
        data.put("out_trade_no", wePayRefundRequest.getOutTradeNo());
        data.put("out_refund_no", wePayRefundRequest.getOutRefundNo());
        data.put("total_fee", wePayRefundRequest.getTotalFee());
        data.put("refund_fee", wePayRefundRequest.getRefundFee());

        if(StringUtils.isNotBlank(wePayRefundRequest.getRefundFeeType())){
            data.put("refund_fee_type", wePayRefundRequest.getRefundFeeType());

        }
        if(StringUtils.isNotBlank(wePayRefundRequest.getRefundDesc())){
            data.put("refund_desc", wePayRefundRequest.getRefundDesc());

        }
        if(StringUtils.isNotBlank(wePayRefundRequest.getRefundAccount())){
            data.put("refund_account", wePayRefundRequest.getRefundAccount());
        }

        if(StringUtils.isNotBlank(wePayRefundRequest.getNotifyUrl())){
            data.put("notify_url", wePayRefundRequest.getNotifyUrl());
        }

        WePayBaseConfig bean = WePayBaseConfig.builder().appId(wePayRefundRequest.getAppId())
                .mchId(wePayRefundRequest.getMchId())
                .certByte(wePayRefundRequest.getCertByte())
                .key(wePayRefundRequest.getKey()).build();

        WxConfigBean wxConfigBean = new WxConfigBean(bean);
        Map<String, String> result = new HashMap<>();
        try {
            WXPay wxPay = new WXPay(wxConfigBean,systemEnvironmentConfig.isProd());
            result = wxPay.refund(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultCode(StatusCode.SUCCESS,result);

    }


}
