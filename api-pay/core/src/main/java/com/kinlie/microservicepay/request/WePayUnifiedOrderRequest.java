package com.kinlie.microservicepay.request;


import com.kinlie.microservicepay.request.base.WePayBaseConfig;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class WePayUnifiedOrderRequest extends WePayBaseConfig {

    /**
     * @Author wwy
     * @Description deviceInfo 设备号
     **/
    private String deviceInfo;

    /**
     * @Author wwy
     * @Description body 商品描述
     **/
    @NotNull(message = "商品描述不能为空")
    private String body;

    /**
     * @Author wwy
     * @Description 商品详情
     **/
    private String detail;

    /**
     * @Author wwy
     * @Description 附加数据
     **/
    private String attach;

    /**
     * @Author wwy
     * @Description 商户订单号
     **/
    @NotNull(message = "商户订单号不能为空")
    private String outTradeNo;


    /**
     * @Author wwy
     * @Description 标价币种 CNY	符合ISO 4217标准的三位字母代码，默认人民币：CNY，详细列表请参见货币类型
     **/
    private String teeType;

    /**
     * @Author wwy
     * @Description 订单总金额，单位为分，详见支付金额
     **/
    @NotNull(message = "订单总金额不能为空")
    private String totalFee;


    /**
     * @Author wwy
     * @Description 终端IP
     **/
    private String spbillCreateIp;

    /**
     * @Author wwy
     * @Description 交易起始时间
     **/
    private String timeStart;


    /**
     * @Author wwy
     * @Description 交易结束时间
     **/
    private String timeExpire;


    /**
     * @Author wwy
     * @Description 订单优惠标记
     **/
    private String goodsTag;


    /**
     * @Author wwy
     * @Description 通知地址notify_url
     **/
    @NotNull(message = "通知地址不能为空")
    private String notifyUrl;


    /**
     * @Author wwy
     * @Description 交易类型
     *     JSAPI -JSAPI支付
     *     ATIVE -Native支付
     *     APP -APP支付
     **/
    @NotNull(message = "交易类型不能为空")
    private String tradeType;


    /**
     * @Author wwy
     * @Description 商品ID
     **/
    private String productId;


    /**
     * @Author wwy
     * @Description  指定支付方式 上传此参数no_credit--可限制用户不能使用信用卡支付
     **/
    private String limitPay;

    /**
     * @Author wwy
     * @Description openid
     **/
    @NotNull(message = "openid不能为空")
    private String openid;


    /**
     * @Author wwy
     * @Description 电子发票入口开放标识
     **/
    private String receipt;


    /**
     * @Author wwy
     * @Description 是否分佣   Y 是
     **/
    private String profitSharing;


    @Data
    class storeInfo{
        private String id;

        private String name;

        private String areaCode;

        private String address;

    }

}
