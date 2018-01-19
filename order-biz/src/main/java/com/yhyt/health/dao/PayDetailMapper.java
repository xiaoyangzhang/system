package com.yhyt.health.dao;

import com.yhyt.health.model.PayDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PayDetailMapper extends BaseDao<PayDetail>{
    int deleteByPrimaryKey(Long id);

    int insert(PayDetail record);

    int insertSelective(PayDetail record);

    PayDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PayDetail record);

    int updateByPrimaryKey(PayDetail record);

	List<PayDetail> findRefundPayDetail(Map<String, Object> params);
//	List<PayDetail> findPersistableList(Map<String, Object> params);
    int updatePayDetail(PayDetail payDetail);

    int deleteByOrderIdAndState(@Param("orderId") String orderId,@Param("states") String states);
    int deletePayDetail(@Param("params") Map<String,Object> params);
}