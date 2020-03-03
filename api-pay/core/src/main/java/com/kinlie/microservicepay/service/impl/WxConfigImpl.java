package com.kinlie.microservicepay.service.impl;

import com.kinlie.microservicepay.dao.WxConfigMapper;
import com.kinlie.microservicepay.pojo.WxConfig;
import com.kinlie.microservicepay.service.IWxConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cc.s2m.web.utils.webUtils.service.BaseServiceImpl;

@Service
public class WxConfigImpl extends BaseServiceImpl<WxConfig, WxConfigMapper, Integer> implements IWxConfig {
    @Autowired
    private WxConfigMapper wxConfigMapper;

    protected WxConfigMapper getDao() {
        return wxConfigMapper;
    }
}