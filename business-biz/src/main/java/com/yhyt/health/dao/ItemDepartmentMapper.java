package com.yhyt.health.dao;

import com.yhyt.health.model.ItemDepartment;
import com.yhyt.health.model.dto.ItemResultDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemDepartmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ItemDepartment record);

    int insertSelective(ItemDepartment record);

    ItemDepartment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ItemDepartment record);

    int updateByPrimaryKey(ItemDepartment record);

    ItemDepartment selectByAppointField(final @Param("field") String field,final @Param("value") Object value);
    List<ItemResultDTO> selectItemsBelongDepts(Long deptId);

    /**
     * 根据商品id获取商品对应医院部门表
     * @param itemId
     * @return
     */
    ItemDepartment selectByItemId(final @Param("itemId") long itemId);
}