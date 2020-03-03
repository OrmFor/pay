package com.kinlie.microservicepay.pojo.entity;

import cc.s2m.web.utils.webUtils.pojo.BaseModelBean;

public class WxConfigEntity extends BaseModelBean {
    private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String mchId;
	private String key;
	private String certString;
	private String appId;
	
    public Integer getId() {
        return id;
    }
	public WxConfigEntity setId(Integer id) {
        this.id = id;
        return this;
    }
    public String getMchId() {
        return mchId;
    }
	public WxConfigEntity setMchId(String mchId) {
        this.mchId = mchId;
        return this;
    }
    public String getKey() {
        return key;
    }
	public WxConfigEntity setKey(String key) {
        this.key = key;
        return this;
    }
    public String getCertString() {
        return certString;
    }
	public WxConfigEntity setCertString(String certString) {
        this.certString = certString;
        return this;
    }
    public String getAppId() {
        return appId;
    }
	public WxConfigEntity setAppId(String appId) {
        this.appId = appId;
        return this;
    }
}