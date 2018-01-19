package com.yhyt.health.dao;

import com.yhyt.health.model.Item;
import com.yhyt.health.model.ItemLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ItemLog record);

    int insertSelective(ItemLog record);

    ItemLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ItemLog record);

    int updateByPrimaryKey(ItemLog record);

    List<ItemLog> selectAll(final @Param("id") Long id);
}