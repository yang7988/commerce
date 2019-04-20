package com.commerce.huayi.utils;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类
 */
public class MD5Tools {

    private static MessageDigest getMd5() {
        try {
            return MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ne) {
            throw new RuntimeException(ne);
        }
    }

    /**
     * MD5 加密，结果为小写的16进制。
     */
    public static String encript(byte[] byteArr) {
        MessageDigest md5 = getMd5();
        md5.update(byteArr);
        return Hex.encodeHexString(md5.digest());
    }

    /**
     * MD5 加密，并转为大写的16进制。
     */
    public static String encryptUpperCase(String str) {
        return encript(str.getBytes()).toUpperCase();
    }


    public static void main(String[] args) {

        System.out.println(MD5Tools.encryptUpperCase("huaYi123@123"));

    }


}
