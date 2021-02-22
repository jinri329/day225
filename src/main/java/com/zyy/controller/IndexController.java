package com.zyy.controller;

import com.zyy.bean.TbItem;
import com.zyy.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/item")
public class IndexController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("/{id}")
    @ResponseBody
    public TbItem findTbItemById(@PathVariable Long id){
        TbItem tbItemById = itemService.findTbItemById(id);
        return tbItemById;
    }
}