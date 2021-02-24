package com.zyy;

import com.zyy.service.ItemService;
import com.zyy.vo.LayuiTableResult;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class MyTest {
    @Autowired
    private ItemService itemService;

    @Test
    public void  demo1(){
        /**
         * 保留两位小数
         * 9.0的时候还要补全0
         *还要将基本数据类型的转为String类型的
         * 当150 模100 得到50
         * 但是105 模100 就是05 最后保留不到两位小数
         * 用三位运算符来判断模的数的大小来补全0
         */
        int price = 105;
        int i1 = price/100;
        int i2 = price%100;
        System.out.println(i1+"."+(i2<10?"0"+i2:i2));
    }

    @Test
    public void demo2(){
        LayuiTableResult count = itemService.searchItem(1,15,"手机",10000L,1000000L,null,1);
    }
}
