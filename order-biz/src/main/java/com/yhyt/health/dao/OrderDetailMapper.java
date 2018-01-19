package com.yhyt.health.dao;

import com.yhyt.health.model.OrderDetail;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OrderDetailMapper extends BaseDao<OrderDetail>{
    int deleteByPrimaryKey(Long id);

    int insert(OrderDetail record);
    
    int insertForfree(OrderDetail record);

    int insertSelective(OrderDetail record);

    OrderDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderDetail record);

    int updateByPrimaryKey(OrderDetail record);

    List<OrderDetail> getUserOrderDetails(Map<String,Object> criterias);
    
    int updateAndCheck(OrderDetail record,Map<String,Object> params);
}