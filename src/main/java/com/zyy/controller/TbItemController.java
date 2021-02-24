package com.zyy.controller;

import com.zyy.service.ItemService;
import com.zyy.vo.LayuiTableResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

@Controller
@RequestMapping("/item")
public class TbItemController {
    @Autowired
    private ItemService itemService;
    //分页展示
    @RequestMapping("/showItemPage")
    @ResponseBody
    public LayuiTableResult findTbItemByPage(Integer page,Integer limit){
        //浏览器输入的地址为：。。。。item/showItemPage?page=1&limit=30
        //就要将json格式的数据返回到页面去
        //ResponseBody 注解会自动将对象转换为json格式输出到页面
        LayuiTableResult result = itemService.findTbItemByPage(page,limit);
        return result;
    }

    @RequestMapping("/itemDel")
    @ResponseBody
    public LayuiTableResult itemDel(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "15")Integer limit,@RequestParam(value = "ids[]") Long[] ids,Integer op){
        LayuiTableResult result = itemService.delTbItemByIds(page,limit,ids,op);
        return result;
    }
    @RequestMapping("/itemDown")
    @ResponseBody
    public LayuiTableResult itemDown(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "15")Integer limit,@RequestParam(value = "ids[]") Long[] ids,Integer op){
        System.out.println();
        LayuiTableResult result = itemService.setTbItemByIds(page,limit,ids,op);
        return result;
    }
    @RequestMapping("/itemUp")
    @ResponseBody
    public LayuiTableResult itemUp(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "15")Integer limit,@RequestParam(value = "ids[]") Long[] ids,Integer op){
        LayuiTableResult result = itemService.upTbItemByIds(page,limit,ids,op);
        return result;
    }

    @RequestMapping("/search")
    @ResponseBody
    public LayuiTableResult itemSearch(@RequestParam(defaultValue = "1") Integer page,Integer limit,String title,@RequestParam(defaultValue = "0") Long price_min,@RequestParam(defaultValue = "1000000000")Long price_max,Integer num,Integer status){
        //这里要确保数据是能接收到，才继续往下
        //System.out.println();
        //解决乱码
        LayuiTableResult result = null;
        try {
            byte[] b =title.getBytes("iso-8859-1");
            String newTitle = new String(b,"UTF-8");
            result =itemService.searchItem(page,limit,newTitle,price_max,price_min,num,status);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
