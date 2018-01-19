package com.yhyt.health.dao;

import com.yhyt.health.model.Item;
import com.yhyt.health.model.dto.ItemBody;
import com.yhyt.health.model.dto.ItemBodyReturnVo;
import com.yhyt.health.model.dto.ItemQueryDTO;
import com.yhyt.health.model.dto.ItemResultDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 商品mapper
 */
@Repository
public interface ItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Item record);

    int insertSelective(Item record);

    Item selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Item record);

    /**
     * 添加乐观锁的更新接口
     * @param record
     * @return
     */
    int updateByPrimaryKeySelectiveWithVersion(Item record);

    /**
     * 添加乐观锁的更新ItemBody接口
     * @param record
     * @return
     */
    int updateItemBodyByPrimaryKeySelectiveWithVersion(ItemBody record);

    int updateByPrimaryKeyWithBLOBs(Item record);

    int updateByPrimaryKey(Item record);

    /**
     * 分页查询商品列表
     * @param itemQueryDTO
     * @return
     */
    List<ItemResultDTO> getItemResultDTOPage(final  ItemQueryDTO itemQueryDTO);

    /**
     * 判断当前商品的排序是否已经存在
     * @param hospitalId
     * @param departmentId
     * @param state
     * @param topOrderId
     * @return
     */
    List<Item> getItemByParams(final @Param("hospitalId") long hospitalId,
                               final @Param("departmentId") long departmentId,
                               final @Param("state") Byte state,
                               final @Param("topOrderId") Byte topOrderId);

    /**
     * 存入页面商品信息
      * @param itemBody
     * @return
     */
    long insertitemBody(ItemBody itemBody);


    /**
     * 修改页面商品信息
     * @param itemBody
     * @return
     */
    int updateitemBodyByPrimaryKeySelective(ItemBody itemBody);

    /**
     * 获取页面信息
     * @return
     */
    ItemBodyReturnVo getItemMessage(final @Param("itemId") Long itemId);

    /**
     * 根据指定字段更新商品关联疾病表
     * @param itemId
     * @param state
     * @return
     */
    int offshelfItem(final @Param("itemId") Long itemId,
                     final @Param("state") Byte state);

    /**
     * 获取最新的商品信息
     * @return
     */
    Item getNewestItem();


    Item selectByOrderId(Long orderId);
    /**
     * 获取需要上/下架的商品列表
     * @param state
     * @param onOffDay
     * @return
     */
    List<Item> getItemNeedShelves(final @Param("state") byte state,
                                  final @Param("onOffDay") Date onOffDay);


    int updateItemStateBatch(@Param("list") List<Item> itemList);


    Item getItemByTaskId(@Param("id") Long id);


}