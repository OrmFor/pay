package com.kinlie.microservicepay.request;


import com.kinlie.microservicepay.request.base.WePayBaseConfig;
import lombok.Data;

@Data
public class WePayAddReceiverRequest extends WePayBaseConfig {

    //商户号	mch_id	是	string(32)
    //子商户号	sub_mch_id	是	string(32)
    //公众账号ID	appid	是	string(32)
    //子商户公众账号ID	sub_appid	否	string(32)
    //随机字符串	nonce_str	是	string(32)
    //签名	sign	是	string(64)
    //签名类型	sign_type	否	string(32)
    //-分账接收方	receiver	是	String(2048)
    //分账接收方类型	type	是	string(32)
    //分账接收方帐号	account	是	string(64)
    //分账接收方全称	name	否	string(1024)
    //与分账方的关系类型	relation_type	是	string(32)
    //自定义的分账关系	custom_relation	否	string(10)
    private String  type;

    private String account;

    private String name;

    private String relationType;

    private String customRelation;
}
