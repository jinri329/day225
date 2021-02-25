package com.zyy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping("/")
    public String showIndex(){
        return "index";
    }

    @RequestMapping("/item")
    public String show(){
        return "showItem";
    }

    //这样就是页面那边点击后 传过来的名称 然后进行拼接.jsp 进行跳转
    @RequestMapping("/{page}")
    public String showIndex(@PathVariable String page){
        return page;
    }

}
