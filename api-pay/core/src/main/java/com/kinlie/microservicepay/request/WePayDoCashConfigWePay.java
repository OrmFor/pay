package com.kinlie.microservicepay.request;


import com.kinlie.microservicepay.request.base.WePayBaseConfig;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class WePayDoCashConfigWePay extends WePayBaseConfig {


    private String deviceInfo;

    private String partnerTradeNo;

    private String openid;

    private String checkName ;

    private String reUserName;

    private BigDecimal amount;//以元为单位

    private String desc;

    private String spbillCreateIp;

}
