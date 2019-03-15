package com.commerce.huayi.constant;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TranslateDict {

    public static final Map<String,Map<String,String>> categoryDist;

    static {
        categoryDist = new ConcurrentHashMap<>(10);
        ConcurrentHashMap<String,String> zhCNMap = new ConcurrentHashMap(50);
        ConcurrentHashMap<String,String> enUSMap = new ConcurrentHashMap(50);
        categoryDist.put(LanguageEnum.ZH_CN.name(), zhCNMap);
        categoryDist.put(LanguageEnum.EN_US.name(), enUSMap);
        zhCNMap.put("big_bluetooth_earphone", "蓝牙大耳机");
        zhCNMap.put("small_bluetooth_earphone", "蓝牙小耳机");
        zhCNMap.put("handfree_earphone", "免提耳机");
        zhCNMap.put("big_wired_earphone", "有线大耳机");
        zhCNMap.put("small_wireless_earphone", "无线小耳机");
        zhCNMap.put("neck_hanging_type_earphone", "脖挂式耳机");
        zhCNMap.put("ear_hanging_type_earphone", "耳挂式耳机");
        zhCNMap.put("ear-canal-fit_earphone", "入耳式耳机");
        zhCNMap.put("metal_handfree_earphone", "金属免提耳机");
        zhCNMap.put("wooden_handfree_earphone", "木制免提耳机");
        zhCNMap.put("plastic_handfree_earphone", "塑胶免提耳机");
        zhCNMap.put("lenticular_wire", "扁线耳机");
        zhCNMap.put("round_wire", "圆线耳机");
        //-----------------------------------------------------


        enUSMap.put("big_bluetooth_earphone", "big_bluetooth_earphone");
        enUSMap.put("small_bluetooth_earphone", "small_bluetooth_earphone");
        enUSMap.put("handfree_earphone", "handfree_earphone");
        enUSMap.put("big_wired_earphone", "big_wired_earphone");
        enUSMap.put("small_wireless_earphone", "small_wireless_earphone");
        enUSMap.put("neck_hanging_type_earphone", "neck_hanging_type_earphone");
        enUSMap.put("ear_hanging_type_earphone", "ear_hanging_type_earphone");
        enUSMap.put("ear-canal-fit_earphone", "ear-canal-fit_earphone");
        enUSMap.put("metal_handfree_earphone", "metal_handfree_earphone");
        enUSMap.put("wooden_handfree_earphone", "wooden_handfree_earphone");
        enUSMap.put("plastic_handfree_earphone", "plastic_handfree_earphone");
        enUSMap.put("lenticular_wire", "lenticular_wire");
        enUSMap.put("round_wire", "round_wire");
    }
}