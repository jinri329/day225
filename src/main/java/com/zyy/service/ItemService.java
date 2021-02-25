package com.zyy.service;

import com.zyy.bean.TbItem;
import com.zyy.vo.LayuiTableResult;
import com.zyy.vo.TaotaoResult;
import org.springframework.web.multipart.MultipartFile;

public interface ItemService {
    /**
     *
     * @param id 商品id
     * @return 根据id查询商品
     */
    TbItem findTbItemById(Long id);

    /**
     *
     * @param page 当前页
     * @param limit 每一页显示条数
     * @return 分页查询商品信息 返回一个指定json格式 页面需要的格式
     *  {"code":0,"msg":"","count":记录条数，"data":[需要展示的信息]}
     */
    LayuiTableResult findTbItemByPage(Integer page,Integer limit);

    /**
     *
     * @param page  当前页
     * @param limit 每一页显示条数
     * @param ids   商品id
     * @param op  代表 1 正常 2 下架 3 删除
     * @return 根据id删除商品 并且返回要展示的商品数据
     */
    LayuiTableResult delTbItemByIds(Integer page, Integer limit, Long[] ids,Integer op);

    /**
     *
     * @param page
     * @param limit
     * @param ids
     * @param op 代表 1 正常 2 下架 3 删除
     * @return 根据id下架商品 并且返回要展示的商品数据
     */
    LayuiTableResult setTbItemByIds(Integer page, Integer limit, Long[] ids, Integer op);

    /**
     *
     * @param page
     * @param limit
     * @param ids
     * @param op
     * @return 根据id上架商品 并且返回要展示的商品数据
     */
    LayuiTableResult upTbItemByIds(Integer page, Integer limit, Long[] ids, Integer op);

    /**
     *
     * @param page
     * @param limit
     * @param price_max
     * @param price_min
     * @param num
     * @param status
     * @return 多条件查询商品
     */
    LayuiTableResult searchItem(Integer page, Integer limit, String title,Long price_max, Long price_min, Integer num, Integer status);

    /**
     *
     * @param file
     * @return 图片上传 返回json格式
     */
    TaotaoResult addImg(MultipartFile file);

    /**
     *
     * @param tbItem
     * @return 添加商品
     */
    TaotaoResult addItem(TbItem tbItem);
}
