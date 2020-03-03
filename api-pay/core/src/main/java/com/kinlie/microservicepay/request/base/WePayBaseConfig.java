package com.kinlie.microservicepay.request.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * 
 * @Description 请求基础参数
 * @author wwy
 * @date 2018年3月22日 下午7:25:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WePayBaseConfig {
    //String appId, String mchId, String key, String certString

    @NotNull(message = "appid不能为空")
    private String appId;

    @NotNull(message = "商户id不能为空")
    private String mchId;

    @NotNull(message = "商户密钥不能为空")
    private String key;

    @NotNull(message = "certString不能为空")
    private byte[] certByte;

}
