package com.zyy.service;

import com.zyy.bean.TbItem;
import com.zyy.vo.LayuiTableResult;

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
}
