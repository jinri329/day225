package com.zyy.service.impl;

import com.zyy.bean.TbItem;
import com.zyy.dao.ItemDao;
import com.zyy.service.ItemService;
import com.zyy.utils.TransformPrice;
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
        for (TbItem tbItem:data) {
            //将每一个数据里面的价格 都处理为保存两位小数存入数据库 所以页面的真正价格为barcode
            tbItem.setBarcode(TransformPrice.trPrice(tbItem.getPrice()));
        }
        return LayuiTableResult.builder(count,data);
    }

    @Override
    public LayuiTableResult delTbItemByIds(Integer page, Integer limit, Long[] ids,Integer op) {
        /**
         * 逻辑删除 就是修改status的值
         */
        int i = itemDao.delTbItemByIds(ids,op);
        Integer count = itemDao.findTbItemByCount();
        List<TbItem> data = itemDao.findTbItemByPage((page-1)*limit,limit);
        if (i<=0){
            return null;
        }
        return LayuiTableResult.builder(count,data);
    }

    @Override
    public LayuiTableResult setTbItemByIds(Integer page, Integer limit, Long[] ids, Integer op) {
        int i = itemDao.setTbItemByIds(ids,op);
        Integer count = itemDao.findTbItemByCount();
        List<TbItem> data = itemDao.findTbItemByPage((page-1)*limit,limit);
        if (i<=0){
            return null;
        }
        return LayuiTableResult.builder(count,data);
    }

    @Override
    public LayuiTableResult upTbItemByIds(Integer page, Integer limit, Long[] ids, Integer op) {
        int i = itemDao.upTbItemByIds(ids,op);
        Integer count = itemDao.findTbItemByCount();
        List<TbItem> data = itemDao.findTbItemByPage((page-1)*limit,limit);

        if (i<=0){
            return null;
        }
        return LayuiTableResult.builder(count,data);
    }

    @Override
    public LayuiTableResult searchItem(Integer page, Integer limit, String title, Long price_max, Long price_min, Integer num, Integer status) {
        List<TbItem> data = itemDao.searchItem((page-1)*limit,limit,title,price_max,price_min,num,status);
        Integer count = itemDao.searchItemByCount(title,price_max,price_min,num,status);
        System.out.println(data);
        return LayuiTableResult.builder(count,data);
    }
}
