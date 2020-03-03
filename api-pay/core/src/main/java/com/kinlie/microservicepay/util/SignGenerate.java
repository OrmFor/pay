package com.kinlie.microservicepay.util;

import java.security.MessageDigest;

public class SignGenerate {

    public static String getMD5(String str) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        char[] charArray = str.toCharArray();
        byte[] byteArray = new byte[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    /**
     * 获取签名
     * 加密规则:initial加密一次取前18位后10位拼接,再加密一次取第5到25位
     *
     * @param initial 商户id+secret+商户id
     * @return 加密密码
     */
    public static String getSign(String initial) {
        String s = SignGenerate.getMD5(initial).substring(0, 18) + SignGenerate.getMD5(initial).substring(SignGenerate.getMD5(initial).length() - 10, SignGenerate.getMD5(initial).length());
        return SignGenerate.getMD5(s.toUpperCase()).substring(4, 25).toUpperCase();
    }
}
