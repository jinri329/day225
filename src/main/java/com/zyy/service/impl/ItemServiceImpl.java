package com.zyy.service.impl;

import com.zyy.bean.TbItem;
import com.zyy.dao.ItemDao;
import com.zyy.service.ItemService;
import com.zyy.vo.LayuiTableResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
   @Autowired
   private ItemDao itemDao;
    @Override
    public TbItem findTbItemById(Long id) {
        TbItem itemById = itemDao.findTbItemById(id);
        return itemById;
    }

    @Override
    public LayuiTableResult findTbItemByPage(Integer page, Integer limit) {
        List<TbItem> data = itemDao.findTbItemByPage((page-1)*limit,limit);
        Integer count = itemDao.findTbItemByCount();
        LayuiTableResult result = new LayuiTableResult();
        result.setCode(0);
        result.setMsg("");
        result.setCount(count);
        result.setData(data);
        return result;
    }
}
