package com.zyy.utils;

public class TransformPrice {
    /**
     * 静态的方法 可以直接调用
     * @param price
     * @return 价格转换工具类
     */
    public static String trPrice(Long price){
        Long i1 = price/100;
        Long i2 = price%100;
        return  i1+"."+(i2<10?"0"+i2:i2);
    }
}
