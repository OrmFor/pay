package com.kinlie.microservicepay.util.wxpay;

import com.github.wxpay.sdk.*;

import java.util.Map;


/**
* @Author wwy
* @Description 微信提现  签名不需要加入sign_type
 *               WXPay中的fillRequestData重写
 *                      appid    ===>   mch_appid
 *                      mch_id   ===>   mchid
* @Date 13:27 2019/12/31
* @Param
* @return
**/
public class WxPayUtils extends WXPay {

    private WxConfigBean config;

    private WXPayConstants.SignType signType;

    private WXPayRequest wxPayRequest;

    public WxPayUtils(WxConfigBean config,boolean useSandbox) throws Exception {
        super(config);
        this.config = config;

        this.wxPayRequest = new WXPayRequest(config);
        if (useSandbox) {
            this.signType = WXPayConstants.SignType.MD5;
        } else {
            this.signType = WXPayConstants.SignType.HMACSHA256;
        }
    }


    @Override
    public Map<String, String> fillRequestData(Map<String, String> reqData) throws Exception {
        reqData.put("mch_appid", this.config.getAppID());
        reqData.put("mchid", this.config.getMchID());
        reqData.put("nonce_str", WXPayUtil.generateNonceStr());
        reqData.put("sign", WXPayUtil.generateSignature(reqData, this.config.getKey(), WXPayConstants.SignType.MD5));
        return reqData;
    }


    @Override
    public String requestWithCert(String urlSuffix, Map<String, String> reqData,
                                  int connectTimeoutMs, int readTimeoutMs) throws Exception {
        String msgUUID = reqData.get("nonce_str");
        String reqBody = WXPayUtil.mapToXml(reqData);
        String resp = this.wxPayRequest.requestWithCert(urlSuffix, msgUUID, reqBody,
                connectTimeoutMs, readTimeoutMs, false);
        return resp;
    }



}
