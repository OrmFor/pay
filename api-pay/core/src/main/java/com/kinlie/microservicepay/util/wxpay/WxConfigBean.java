package com.kinlie.microservicepay.util.wxpay;


import com.github.wxpay.sdk.IWXPayDomain;
import com.github.wxpay.sdk.WXPayConfig;
import com.kinlie.microservicepay.pojo.WxConfig;
import com.kinlie.microservicepay.request.base.WePayBaseConfig;
import org.springframework.context.annotation.Scope;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;

public class WxConfigBean extends WXPayConfig {

    private String appId;
    private String mchId;
    private String key;
    private byte[] certData;

    private WePayBaseConfig wxConfig;

    public WxConfigBean(String appId, String mchId, String key, byte[] certData) {
        this.appId = appId;
        this.mchId = mchId;
        this.key = key;
        this.certData = certData;
    }

    public WxConfigBean(WePayBaseConfig wxConfig){
        this.appId = wxConfig.getAppId();
        this.mchId = wxConfig.getMchId();
        this.key = wxConfig.getKey();
        this.certData = wxConfig.getCertByte();
    }

    public String getAppID() {
        return this.appId;
    }

    public String getMchID() {
        return this.mchId;
    }

    public  String getKey() {
        return this.key;
    }

    @Override
    public InputStream getCertStream() {
        ByteArrayInputStream certBis;
        certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 2000;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 10000;
    }

    @Override
    public IWXPayDomain getWXPayDomain(){
        return WXPayDomainSimpleImpl.instance();
    }

}
