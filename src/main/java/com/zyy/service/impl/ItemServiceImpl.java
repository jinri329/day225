package com.zyy.service.impl;

import com.zyy.bean.TbItem;
import com.zyy.dao.ItemDao;
import com.zyy.service.ItemService;
import com.zyy.utils.IDUtils;
import com.zyy.utils.PicUtils;
import com.zyy.utils.TransformPrice;
import com.zyy.vo.LayuiTableResult;
import com.zyy.vo.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Override
    public TaotaoResult addImg(MultipartFile file) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String folder = format.format(new Date());
        String filename = file.getOriginalFilename();
        //全新的图片名称
        filename = PicUtils.getPicName(filename);
        File f = new File("S://updateimg",folder);
        if (!f.exists()){
            f.mkdir();
        }
        try {
            file.transferTo(new File(f,filename));
            return TaotaoResult.ok("http://localhost/"+folder+"/"+filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public TaotaoResult addItem(TbItem tbItem) {
        //判断标题非空 添加商品时全部都要判断非空，但是我们只是做demo就省略一下
        if(tbItem.getTitle().equals("")){
            return TaotaoResult.build(500,"no","商品添加失败，商品名称不能为空");
        }
        tbItem.setId(IDUtils.genItemId());
        tbItem.setcId(560L);
        tbItem.setStatus((byte) 1);
        Date date = new Date();
        tbItem.setCreated(date);
        tbItem.setUpdated(date);
        int count = itemDao.addItem(tbItem);
        if (count<=0){
            return TaotaoResult.build(500,"no","商品添加失败");
        }
        return TaotaoResult.ok("添加成功~");
    }
}
