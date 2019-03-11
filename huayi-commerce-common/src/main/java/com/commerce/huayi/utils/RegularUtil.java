package com.commerce.huayi.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularUtil {
    private static String ipReg = "((25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))";

    public static boolean checkIp(String ip) {
        return checkReg(ip,ipReg);
    }

    public static boolean checkReg(String ip,String reg) {
        Pattern regex = Pattern.compile(reg);
        Matcher matcher = regex.matcher(ip);
        return matcher.matches();
    }

    public static void main(String[] args) {
        System.out.println(checkIp("172.16.4.000034"));
    }
}