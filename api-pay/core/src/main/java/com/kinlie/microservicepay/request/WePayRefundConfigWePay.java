package com.kinlie.microservicepay.request;


import com.kinlie.microservicepay.request.base.WePayBaseConfig;
import lombok.Data;

@Data
public class WePayRefundConfigWePay extends WePayBaseConfig {

    //随机字符串	nonce_str	是	String(32)
    //签名	sign	是	String(32)
    //签名类型	sign_type	否	String(32)
    //微信订单号	transaction_id	二选一	String(32)
    //商户订单号	out_trade_no		String(32)
    //商户退款单号	out_refund_no	是	String(64)
    //订单金额	total_fee	是	Int
    //退款金额	refund_fee	是	Int
    //退款货币种类	refund_fee_type	否	String(8)
    //退款原因	refund_desc	否	String(80)
    //退款资金来源	refund_account	否	String(30)
    //退款结果通知url	notify_url	否	String(256)

    private String transactionId;
    private String outTradeNo;
    private String outRefundNo;
    private String totalFee;
    private String refundFee;
    private String refundFeeType;
    private String refundDesc;
    private String refundAccount;
    private String notifyUrl;


}
