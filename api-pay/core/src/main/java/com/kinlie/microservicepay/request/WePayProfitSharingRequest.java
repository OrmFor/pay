package com.kinlie.microservicepay.request;


import com.alibaba.fastjson.JSONObject;
import com.kinlie.microservicepay.request.base.WePayBaseConfig;
import lombok.Data;

@Data
public class WePayProfitSharingRequest extends WePayBaseConfig {
    //商户号	mch_id	是	string(32)
    //子商户号	sub_mch_id	是	string(32)
    //公众账号ID	appid	是	string(32)
    //子商户公众账号ID	sub_appid	否	string(32)
    //随机字符串	nonce_str	是	string(32)
    //签名	sign	是	string(64)
    //签名类型	sign_type	否	string(32)


    //微信订单号	transaction_id	是	string(32)
    //商户分账单号	out_order_no	是	string(64)
    //-分账接收方列表	receivers	是	String(10240)
    //-分账接收方类型	type	是	string(32)
    //-分账接收方帐号	account	是	string(64)
    //-分账金额	amount	是	int
    //-分账描述	description	是	string(80)

    private String transactionId;
    private String outOrderNo;
    private JSONObject receivers;

    /*type;
    private String account;
    private String amount;
    private String description;*/
}
