package com.yhyt.health.service;

import com.yhyt.health.model.Item;
import com.yhyt.health.model.dto.ItemBody;
import com.yhyt.health.model.dto.ItemBodyReturnVo;
import com.yhyt.health.model.dto.ItemQueryDTO;
import com.yhyt.health.model.dto.ItemResultDTO;
import com.yhyt.health.spring.AppResult;
import com.yhyt.health.util.Page;

import java.util.List;

/**
 * 商品service
 */
public interface ItemService {
    int deleteByPrimaryKey(Long id);

    int insert(Item record);

    int insertSelective(Item record);

    Item selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Item record);

    int updateByPrimaryKeyWithBLOBs(Item record);

    int updateByPrimaryKey(Item record);

    /**
     * 分页查询商品列表
     * @param itemQueryDTO
     * @return
     */
    Page<ItemResultDTO> getItemResultDTOPage(ItemQueryDTO itemQueryDTO);

    /**
     * 查询科室下的服务
     * @param deptId
     * @return
     */
    List<ItemResultDTO> getItemDTOsBelongDept(Long deptId);

    /**
     * 生成商品id＋code
     * @return
     */
    String createItemCode();

    /**
     * 保存／修改商品信息
     * @param itemBody
     * @return
     */
    AppResult insertOrUpdateItemAndDisease(final ItemBody itemBody);

    /**
     * 上架商品，需要校验商品信息是否录入完全
     * @return
     */
    AppResult shelvesItem(final Long itemId,final Byte state,final String currentName);

    /**
     * 根据商品id和商品状态更新商品状态
     * @param itemId
     * @param state
     * @return
     */
    AppResult changeItemState(final Long itemId,Byte state);

    /**
     * 商品的详细信息查询接口
     * @return
     */
    ItemBodyReturnVo getItemMessage(final Long itemId);

    /**
     * 下架商品
     * @param itemId
     * @param state
     * @return
     */
    AppResult offshelfItem(final Long itemId,final Byte state,final String currentName);



    /**
     * 商品上/下架
     * @param item
     * @return
     */
    int updateItemState(Item item);

    /**
     * 获取最新的商品信息
     * @return
     */
    Item getNewestItem();

    /**
     * 商品上下架状态变更
     */
    void itemStateChange();

    /**
     * 根据订单 id 查询对应的商品信息
     * @param orderId
     * @return
     */
    Item selectByOrderId(Long orderId);

}
