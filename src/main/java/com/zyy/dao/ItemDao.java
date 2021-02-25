package com.zyy.dao;

import com.zyy.bean.TbItem;
import com.zyy.vo.LayuiTableResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemDao {
    /**
     *
     * @param id 商品id
     * @return 根据商品id查找商品
     */
    TbItem findTbItemById(Long id);

    /**
     *
     * @param index 当前页
     * @param limit 每一页的展示条数
     * @return 分页查询商品信息
     */
    List<TbItem> findTbItemByPage(@Param("index") int index, @Param("limit") Integer limit);

    /**
     *
     * @return 查询总记录条数
     */
    Integer findTbItemByCount();

    int delTbItemByIds(@Param("ids") Long[] ids, @Param("status") Integer op);

    int setTbItemByIds(@Param("ids") Long[] ids,@Param("status") Integer op);

    int upTbItemByIds(@Param("ids")Long[] ids,@Param("status") Integer op);

    /**
     *
     * @param index
     * @param limit
     * @param title
     * @param price_max
     * @param price_min
     * @param num
     * @param status
     * @return 多条件搜索商品信息
     */
    List<TbItem> searchItem(@Param("index")int index, @Param("limit") Integer limit, @Param("title") String title,@Param("price_max") Long price_max,@Param("price_min") Long price_min,@Param("num") Integer num,@Param("status") Integer status);

    Integer searchItemByCount(@Param("title")String title, @Param("price_max")Long price_max, @Param("price_min")Long price_min, @Param("num")Integer num, @Param("status")Integer status);

    int addItem(TbItem tbItem);
}
