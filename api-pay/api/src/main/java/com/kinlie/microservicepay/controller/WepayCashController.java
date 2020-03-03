package com.kinlie.microservicepay.controller;


import com.github.wxpay.sdk.WXPayConstants;
import com.kinlie.microservicepay.common.SystemEnvironmentConfig;
import com.kinlie.microservicepay.controller.base.BaseController;
import com.kinlie.microservicepay.core.ResultCode;
import com.kinlie.microservicepay.core.StatusCode;
import com.kinlie.microservicepay.request.WePayDoCashConfigWePay;
import com.kinlie.microservicepay.request.base.WePayBaseConfig;
import com.kinlie.microservicepay.util.wxpay.WxConfigBean;
import com.kinlie.microservicepay.util.wxpay.WxPayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
@RequestMapping("/payapi/1.0")
public class WepayCashController extends BaseController {

    @Autowired
    private SystemEnvironmentConfig systemEnvironmentConfig;

    /**
     * @Author wwy
     * @Description 提现接口
     * @Date 16:29 2020/3/1
     * @Param
     * @return
     **/
    @RequestMapping("/doCash")
    public ResultCode doCash(WePayDoCashConfigWePay doCashRequest){
        Map<String,String> data = new HashMap<String, String>();
        data.put("partner_trade_no", doCashRequest.getPartnerTradeNo());
        data.put("openid",doCashRequest.getOpenid());
        data.put("check_name", doCashRequest.getCheckName());
        data.put("re_user_name", doCashRequest.getReUserName());
        data.put("amount", String.valueOf(doCashRequest.getAmount().multiply(new BigDecimal(100))));
        data.put("desc", doCashRequest.getDesc());
        if(StringUtils.isNotBlank(doCashRequest.getSpbillCreateIp())){
            data.put("spbill_create_ip", doCashRequest.getSpbillCreateIp());
        }
        if(StringUtils.isNotBlank(doCashRequest.getDeviceInfo())){
            data.put("device_info", doCashRequest.getDeviceInfo());
        }
        WePayBaseConfig wePayBaseConfig = WePayBaseConfig.builder().appId(doCashRequest.getAppId())
                .mchId(doCashRequest.getMchId())
                .key(doCashRequest.getKey())
                .certByte(doCashRequest.getCertByte()).build();
        WxConfigBean wxConfigBean = new WxConfigBean(wePayBaseConfig);
        String result = "";
        try {
            WxPayUtils wxPay = new WxPayUtils(wxConfigBean,systemEnvironmentConfig.isProd());
            Map<String,String> dataSign = wxPay.fillRequestData(data);
            /*for (Iterator i = dataSign.keySet().iterator(); i.hasNext();) {
                Object obj = i.next();
               // System.out.println(obj);// 循环输出key
                System.out.println("key=" + obj + " value=" + dataSign.get(obj));
            }*/

            result = wxPay.requestWithCert(WXPayConstants.TRANSFERS_URL_SUFFIX,dataSign,1000,1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultCode(StatusCode.SUCCESS,result);
    }

}
